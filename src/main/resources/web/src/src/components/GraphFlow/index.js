import React, {Component} from 'react';
import './g6';
import G6 from '@antv/g6';
import { cloneDeep } from "lodash";
// import G6Plugins from '@antv/g6/build/plugins';
import  "./index.less"

const MIN_ARROW_SIZE = 3;
const data1 = {
    roots: [
      {
        main: '主指标一',
        value: 123111,
        percent: '100%',
        type: 'a',
        children: [
          {
            main: '指标 1',
            value: 12312,
            percent: '39%',
            type: 'b',
            
          },
          {
            main: '指标 2',
            value: 12312,
            percent: '79%',
            type: 'b',
            children: [
              {
                main: '指标 2.1',
                value: 111,
                percent: '90%',
                type: 'a',
              },
              {
                main: '指标 2.2',
                value: 222,
                percent: '90%',
                type: 'a',
              },
            ],
          },
        ],
      },
    ],
  }
export default class GraphFlow extends Component {
    constructor(props,context){
        super(props,context)
    }

    

  componentDidMount () {
      const that = this;
      that.g6RegisterInit();
    const tree = new G6.Tree ({
      container: 'mountNode',
      forceFit: true,
      renderer: 'svg',
      layout: new G6.Layouts.CompactBoxTree ({
        // direction: 'LR', // 方向（LR/RL/H/TB/BT/V）
        getHGap: function getHGap () /* d */ {
          // 横向间距
          return 80;
        },
        getVGap: function getVGap () /* d */ {
          // 竖向间距
          return 24;
        },
        direction: 'TB',
      }),
      fitView: 'cc',
    });
    tree.node ({
      shape: 'card',
    });
    tree.edge ({
      shape: 'VHV',
      endArrow: true
    });
    tree.on ('node:click', ev => {
      const {domEvent, item} = ev;
      const {target} = domEvent;
      const {collapsed, main} = item.getModel ();
      console.log(item.getModel())
      console.log(target.className)
      if (target.className === 'ce-button bottom') {
        if (collapsed) {
          tree.update (item, {
            collapsed: false,
          });
        } else {
          tree.update (item, {
            collapsed: true,
          });
        }
      }else if(target.className === 'ce-button top'){
        // tree.remove(parent)
        console.log(data1)
        // const { data } = that.state;
        // const { roots } = data;
        // const supplyData = {roots:[]};
        // const find = src => {
        //     if(supplyData.roots.length === 0){
        //         if(src.main === main){
        //             supplyData.roots = src;
        //         }else if(src.children && src.children.length > 0){
        //             src.children.map(item1 => find(item1))
        //         }
        //     }
        // }
        // roots.map(obj => find(obj))

        // that.setState({ 
        //     data: supplyData
        // })
        // console.log(that.state.data)
        // tree.read(data1)
      }
    });
    tree.read (cloneDeep(data1));
  }

  g6RegisterInit = () => {
       /*
    * 模版已经默认按以下链接安装了最新版 G6 和 G6Plugins
    * https://unpkg.com/@antv/g6/build/g6.js
    * https://unpkg.com/@antv/g6/build/plugins.js
    * 如果你无法访问上述链接，你需要自己手动安装能访问的链接安装 G6 
    */

    G6.registerEdge ('VHV', {
        getPath (item) {
          const points = item.getPoints ();
          const start = points[0];
          const end = points[points.length - 1];
          const vgap = end.y - start.y;
          const ygap = vgap / 4 * 3;
          return [
            ['M', start.x, start.y],
            ['L', start.x, start.y + ygap],
            ['L', end.x, start.y + ygap],
            ['L', end.x, end.y],
          ];
        },
        endArrow: {
          path(item) {
              const keyShape = item.getKeyShape();
              let lineWidth = keyShape.attr('lineWidth');
              lineWidth = lineWidth > MIN_ARROW_SIZE ? lineWidth : MIN_ARROW_SIZE;
              const width = lineWidth * 10 / 3;
              const halfHeight = lineWidth * 4 / 3;
              const radius = lineWidth * 4;
              return [
              [ 'M', -width, halfHeight ],
              [ 'L', 0, 0 ],
              [ 'L', -width, -halfHeight ],
              [ 'A', radius, radius, 0, 0, 1, -width, halfHeight ],
              [ 'Z' ]
              ];
            },
            shorten(item) {
              const keyShape = item.getKeyShape();
              const lineWidth = keyShape.attr('lineWidth');
              return (lineWidth > MIN_ARROW_SIZE ? lineWidth : MIN_ARROW_SIZE) * 3.1;
            },
            style(item) {
              const keyShape = item.getKeyShape();
              const { strokeOpacity, stroke } = keyShape.attr();
              return {
                fillOpacity: strokeOpacity,
                fill: stroke
              };
            }
        }
      
      });
  
      G6.registerNode ('card', {
          collapseButtonUrl: 'https://gw.alipayobjects.com/zos/rmsportal/GGzWwlTjflbJHmXhjMXg.svg',
          expandButtonUrl: 'https://gw.alipayobjects.com/zos/rmsportal/DzWdTiwanggjaWKwcnWZ.svg',
        draw (item) {
          const group = item.getGraphicGroup ();
          const {
            main,
            value,
            percent,
            type,
            collapsed,
            children,
          } = item.getModel ();
          const width = 170;
          const height = 46;
          const buttonWidth = 14;
          const buttonHeight = 14;
          const hasParent = true;
          const showParent = false;
          let topButton = ''; let bottomButton = '';
          if (children && children.length > 0) {
              bottomButton =
              `<img class="ce-button bottom" src=${ 
              collapsed ? this.expandButtonUrl : this.collapseButtonUrl 
              }>`;
          }
          if(hasParent){
              topButton = `<img class="ce-button top" src=${ 
                  showParent ? this.expandButtonUrl : this.collapseButtonUrl 
                  }>`
          }
          const html = G6.Util.createDOM (`
          <div class="card-container">
            <h1 class="main-text">${main}</h1>
              <p class="value-text">${value}</p>
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
                y: - buttonHeight,
                width: buttonWidth,
                height: buttonHeight,
                html: topButton,
              },
            });
          return keyShape;
        },
        anchor: [[0.5, 0], [0.5, 1]],
      });
  }

  render () {
    return <div style={{width: '100%', height: '100%'}} id="mountNode" />;
  }
}
