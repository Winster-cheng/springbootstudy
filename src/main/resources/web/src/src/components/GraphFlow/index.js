import React, {Component} from 'react';
import {connect} from 'dva';
import {Spin} from 'antd';
import {uniq, isEqual} from 'lodash';
import './g6';
import G6 from '@antv/g6';
import G6Plugins from '@antv/g6/build/plugins';
import {statusClassName} from '@/utils/constant';
import './index.less';
import collapseButton from '../../assets/collapse_btn.svg';
import expandButton from '../../assets/expand_btn.svg';

const MIN_ARROW_SIZE = 3;

// 作业计划页面 isTaskInstance false/undefined 的情况使用一下props
// 任务实例页面 isTaskInstance 为true 的情况使用一下props
@connect (({task, loading}) => ({
  newNodes: task.newNodes,
  graphNodeExpandLoading: loading.effects['task/fetchGraphNode'],
  newInstanceNodes: task.newInstanceNodes,
  graphInstanceNodeExpandLoading: loading.effects['task/fetchInstanceNode'],
}))
class GraphFlow extends Component {
  lastPoint = null;

  constructor (props) {
    super (props);
    this.state = {
      graph: null,
      graphDependencies: {},
      nodeId: 0,
      titleDetail: ""
    };
  }

  static getDerivedStateFromProps (nextProps, prevState) {
    const {isTaskInstance} = nextProps;
    const newNodesKey = isTaskInstance ? 'newInstanceNodes' : 'newNodes';
    if (
      !nextProps.loading &&
      prevState.graph &&
      nextProps.graphDependencies &&
      (!isEqual (prevState.nodeId, nextProps.nodeId) ||
        !isEqual (prevState.graphDependencies, nextProps.graphDependencies))
    ) {
      prevState.graph.read (nextProps.graphDependencies);
      return {
        ...prevState,
        graphDependencies: nextProps.graphDependencies,
        nodeId: nextProps.nodeId,
        titleDetail: ""
      };
    }
    if (prevState.graph && nextProps[newNodesKey].result) {
      const {graph} = prevState;
      const {isTop, list} = nextProps[newNodesKey];
      list.forEach (node => {
        if (graph.find (node.id)) return false;
        graph.add ('node', node);
        const {input = [], output = []} = node;
        if (isTop) {
          output.forEach (target => {
            if (graph.find (target)) {
              graph.add ('edge', {
                source: node.id,
                target,
              });
              graph.update (target, {
                input: uniq (graph.find (target).model.input.concat (node.id)),
              });
            }
          });
        } else {
          input.forEach (source => {
            if (graph.find (source)) {
              graph.add ('edge', {
                source,
                target: node.id,
              });
              graph.update (source, {
                output: uniq (
                  graph.find (source).model.output.concat (node.id)
                ),
              });
            }
          });
        }
      });
    }
    return null;
  }

  componentDidMount () {
    const {isTaskInstance} = this.props;
    const fetchMoreEffectName = `task/${isTaskInstance ? 'fetchInstanceNode' : 'fetchGraphNode'}`;
    this.graphInit (fetchMoreEffectName);
  }

  graphInit = fetchMoreEffectName => {
    const that = this;
    const {payloadKey} = this.props;
    const toggleDomVisible = (id, isShow) => {
      Array.prototype.forEach.call (
        document.getElementsByClassName (`ce-button-${id}`),
        dom => {
          dom.style.display = `${isShow ? 'inline-block' : 'none'}`;
        }
      );
    };
    const hideDetail = () => {
      Array.prototype.forEach.call (
        document.getElementsByClassName("card-container"),
        dom => {
          dom.className = dom.className.split ('selected').join ('');
        }
      );
    }
    that.g6RegisterInit ();
    const graph = new G6.Graph ({
      container: 'mountNode',
      fitView: 'cc',
      renderer: 'svg',
      plugins: [new G6Plugins['layout.dagre'] ()],
      defaultIntersectBox: 'card', // 使用矩形包围盒
    });
    graph.node ({
      shape: 'card',
    });
    graph.edge ({
      shape: 'VHV',
      endArrow: true,
    });
    graph.on ('node:mouseenter', ev => {
      const {item} = ev;
      const {id} = item.getModel ();
      toggleDomVisible (id, true);
    });
    graph.on ('node:mouseleave', ev => {
      const {item} = ev;
      const {id} = item.getModel ();
      toggleDomVisible (id, false);
    });
    graph.on ('node:click', ev => {
      const {item, domEvent} = ev;
      domEvent.stopPropagation();
      const {id, output, input, hasChildren, hasParent} = item.getModel ();
      let {target} = domEvent;
      let {className = ""} = target;
      if(!className.indexOf) return false;
      if (
        className.indexOf ('ce-button') > -1 &&
        className.indexOf ('bottom') > -1
      ) {
        const fdAndRm = parentId => {
          const node = graph.find (parentId);
          const {model = {}} = node;
          const {output = [], input = []} = model;
          output.forEach (childId => fdAndRm (childId));
          input.forEach (parentId =>
            graph.update (parentId, {
              output: [],
            })
          );
          graph.remove (node);
        };
        if (output.length > 0) {
          output.forEach (nodeId => {
            fdAndRm (nodeId);
          });
        } else if (hasChildren) {
          that.props.dispatch ({
            type: fetchMoreEffectName,
            payload: {
              [payloadKey]: id,
              isTop: false,
            },
          });
        }
      } else if (
        className.indexOf ('ce-button') > -1 &&
        className.indexOf ('top') > -1
      ) {
        const compareStack = [id];
        const fdAndRm = nodeId => {
          const node = graph.find (nodeId);
          if (node) {
            compareStack.push (nodeId);
            const {model = {}} = node;
            const {output = [], input = []} = model;
            uniq (input.concat (output)).forEach (childId => {
              if (compareStack.indexOf (childId) === -1) {
                fdAndRm (childId);
              }
            });
            graph.remove (node);
          }
        };
        if (input.length > 0) {
          input.forEach (nodeId => {
            fdAndRm (nodeId);
          });
          graph.update (id, {
            input: [],
          });
        } else if (hasParent) {
          that.props.dispatch ({
            type: fetchMoreEffectName,
            payload: {
              [payloadKey]: id,
              isTop: true,
            },
          });
        }
      } else {
        hideDetail();
        if(className.indexOf ('card-container') === -1){
          target = target.parentElement;
          className = target.className || "";
        }
        if (className.indexOf ('selected') > -1) {
          target.className = className.split ('selected').join ('');
          that.setState({
            titleDetail: ""
          })
        } else {
          target.className = `${className} selected`;
          that.setState({
            titleDetail: target.firstElementChild.getAttribute("titledetail") || ""
          })
        }
      }

      graph.on ('drag', ev => {
        if (that.lastPoint) {
          graph.translate (
            ev.domX - that.lastPoint.x,
            ev.domY - that.lastPoint.y
          );
        }
        that.lastPoint = {
          x: ev.domX,
          y: ev.domY,
        };
      });

      graph.on ('dragend', ev => {
        that.lastPoint = null;
      });

      graph.setFitView ('cc');
    });
    graph.on('click', ev => {
      const {domEvent} = ev;
      const {target} = domEvent;
      if(target.id.startsWith("canvas")){
        hideDetail();
        that.setState({
          titleDetail: ""
        })
      }
    });
    that.setState ({
      graph,
    });
  };

  g6RegisterInit = () => {
    const {isTaskInstance} = this.props;
    G6.registerEdge ('VHV', {
      draw (item) {
        const group = item.getGraphicGroup ();
        const path = this.getPath (item);
        return group.addShape ('path', {
          attrs: {
            path,
            stroke: '#00BBDB',
            lineWidth: 2,
          },
        });
      },
      getPath (item) {
        const points = item.getPoints ();
        const start = points[0];
        const end = points[points.length - 1];
        const vgap = end.y - start.y;
        const ygap = vgap / 2;
        return [
          ['M', start.x, start.y],
          ['L', start.x, start.y + ygap],
          ['L', end.x, start.y + ygap],
          ['L', end.x, end.y],
        ];
      },
      endArrow: {
        path (item) {
          const keyShape = item.getKeyShape ();
          let lineWidth = keyShape.attr ('lineWidth');
          lineWidth = lineWidth > MIN_ARROW_SIZE ? lineWidth : MIN_ARROW_SIZE;
          const width = lineWidth * 10 / 3;
          const halfHeight = lineWidth * 4 / 3;
          const radius = lineWidth * 4;
          return [
            ['M', -width, halfHeight],
            ['L', 0, 0],
            ['L', -width, -halfHeight],
            ['A', radius, radius, 0, 0, 1, -width, halfHeight],
            ['Z'],
          ];
        },
        shorten (item) {
          const keyShape = item.getKeyShape ();
          const lineWidth = keyShape.attr ('lineWidth');
          return (
            (lineWidth > MIN_ARROW_SIZE ? lineWidth : MIN_ARROW_SIZE) * 3.1
          );
        },
        style (item) {
          const keyShape = item.getKeyShape ();
          const {strokeOpacity, stroke} = keyShape.attr ();
          return {
            fillOpacity: strokeOpacity,
            fill: stroke,
          };
        },
      },
    });

    G6.registerNode ('card', {
      draw (item) {
        const group = item.getGraphicGroup ();
        const {
          name,
          id,
          hasChildren,
          hasParent,
          input = [],
          output = [],
          status = {},
        } = item.getModel ();
        const {id: statusId, chineseName} = status;
        const width = 188;
        const height = 46;
        const buttonWidth = 14;
        const buttonHeight = 14;
        let topButton = '';
        let bottomButton = '';
        if (hasChildren) {
          bottomButton = `<img class="ce-button ce-button-${id} bottom" src=${output.length > 0 ? collapseButton : expandButton}>`;
        }
        if (hasParent) {
          topButton = `<img class="ce-button ce-button-${id} top" src=${input.length > 0 ? collapseButton : expandButton}>`;
        }
        const html = G6.Util.createDOM (`
          <div class="card-container ${isTaskInstance ? statusClassName[statusId] : ''}">
            <h1 class="main-text ellipsis" titledetail="${name}">
            ${isTaskInstance ? '<span class="status-icon"></span>' : ''}
            ${name}
            </h1>
            <p class="value-text ellipsis">
            ${isTaskInstance ? chineseName : name}
            </p>
          </div>
        `);
        const keyShape = group.addShape ('dom', {
          attrs: {
            x: 0,
            y: 0,
            width,
            height,
            html,
          },
        });
        group.addShape ('dom', {
          attrs: {
            x: width / 2 - buttonWidth / 2,
            y: height - buttonHeight,
            width: buttonWidth,
            height: buttonHeight,
            html: bottomButton,
          },
        });
        group.addShape ('dom', {
          attrs: {
            x: width / 2 - buttonWidth / 2,
            y: -buttonHeight,
            width: buttonWidth,
            height: buttonHeight,
            html: topButton,
          },
        });
        return keyShape;
      },
      anchor: [[0.5, 0], [0.5, 1]],
    });
  };

  render () {
    const {
      loading,
      graphNodeExpandLoading,
      graphInstanceNodeExpandLoading,
      isTaskInstance,
    } = this.props;
    const expandLoading = isTaskInstance
      ? graphInstanceNodeExpandLoading
      : graphNodeExpandLoading;
    const commonStyle = {
      width: '100%',
      height: '100%',
    };
    const spinStyle = {
      display: loading ? 'flex' : 'none',
      justifyContent: 'center',
      alignItems: 'center',
      ...commonStyle,
    };
    const chartStyle = {
      display: !loading ? 'inline-block' : 'none',
      ...commonStyle,
    };

    const expandSpinStyle = {
      ...spinStyle,
      display: expandLoading ? 'flex' : 'none',
      position: 'absolute',
      zIndex: 1,
    };
    return (
      <React.Fragment>
        <Spin style={spinStyle} />
        <Spin style={expandSpinStyle} />
        <div style={chartStyle} id="mountNode" />
        <h1 className="titleDetail" style={{display: this.state.titleDetail ? "inline-block" : "none"}}>节点名称：{this.state.titleDetail}</h1>
      </React.Fragment>
    );
  }
}
export default GraphFlow;
