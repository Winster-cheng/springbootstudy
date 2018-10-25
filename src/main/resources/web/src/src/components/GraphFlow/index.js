import React, {Component} from 'react';
import './g6';
import G6 from '@antv/g6';
import {cloneDeep} from 'lodash';
import G6Plugins from '@antv/g6/build/plugins';
import './index.less';
import collapseButton from '../../assets/collapse_btn.svg';
import expandButton from '../../assets/expand_btn.svg';

const MIN_ARROW_SIZE = 3;

export default class GraphFlow extends Component {
  constructor (props, context) {
    super (props, context);
    this.state = {
      data: {
        nodes: [
          {
            id: 1,
            name: '收集日志',
            hasParent: false,
            hasChildren: true,
            input: [],
            output: [2, 3],
          },
          {
            id: 2,
            name: '入 es 集群',
            hasParent: true,
            hasChildren: true,
            input: [1],
            output: [4],
          },
          {
            id: 3,
            name: '入 hdfs',
            hasParent: true,
            hasChildren: true,
            input: [1],
            output: [4],
          },
          {
            id: 4,
            name: 'hive 计算',
            hasParent: true,
            hasChildren: true,
            input: [2, 3],
            output: [5],
          },
          {
            id: 5,
            name: 'report',
            hasParent: true,
            hasChildren: false,
            input: [4],
            output: [],
          },
        ],
        edges: [
          {
            source: 1,
            target: 2,
          },
          {
            source: 1,
            target: 3,
          },
          {
            source: 3,
            target: 4,
          },
          {
            source: 2,
            target: 4,
          },
          {
            source: 4,
            target: 5,
          },
        ],
      },
    };
  }

  componentDidMount () {
    const that = this;
    const toggleDomVisible = (id, isShow) => {
      Array.prototype.forEach.call (
        document.getElementsByClassName (`ce-button-${id}`),
        dom => {
          dom.style.display = `${isShow ? 'inline-block' : 'none'}`;
        }
      );
    };
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
    graph.read (this.state.data);
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
        console.log(ev)
      const {item, domEvent} = ev;
      const {id} = item.getModel ();
      const { target } = domEvent;
      const { className } = target;
      if (className.indexOf('ce-button') > -1 &&  className.indexOf('bottom') > -1) {

        console.log(1)
      } else if (className.indexOf('ce-button') > -1 &&  className.indexOf('top') > -1) {
        console.log(2)
      }
        // const {data} = that.state;
        // const {roots} = data;
        // const supplyData = {roots: []};
        // const find = src => {
        //   if (supplyData.roots.length === 0) {
        //     if (src.main === main) {
        //       supplyData.roots.push (src);
        //     } else if (src.children && src.children.length > 0) {
        //       src.children.map (item1 => find (item1));
        //     }
        //   }
        // };
        // roots.map (obj => find (obj));
    //   }
    });
  }

  g6RegisterInit = () => {
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
        } = item.getModel ();
        const width = 170;
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
          <div class="card-container">
            <h1 class="main-text">${name}</h1>
              <p class="value-text">${name}</p>
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
    return <div style={{width: '100%', height: '100%'}} id="mountNode" />;
  }
}
