import React, { Component } from 'react';
import { connect } from 'dva';
import router from 'umi/router';
import { Tree, Row, Col, Button, Spin, Icon, Tabs, Menu, Dropdown } from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import {UnControlled as CodeMirror} from 'react-codemirror2';
import Folder from "../../assets/icon_folder.svg";
import OpenFolder from "../../assets/icon_folder_open.svg";

require('codemirror/mode/javascript/javascript');
require('codemirror/lib/codemirror.css');
require('codemirror/theme/neo.css');

import styles from "./TaskSubmit.less"

const { TreeNode } = Tree;
const { TabPane } = Tabs;

@connect(({ task, loading }) => ({
  taskTreeData: task.treeData,
  treeDataLoading: loading.effects["task/fetchTaskTreeData"],
}))
class TaskSubmit extends Component {
  constructor(props, context) {
    super(props, context);
    this.state = {
      initCode: 'const a = 0;',
      codeValue: "",
      panes: [
        { title: '任务一', key: '1' },
        { title: '任务一任务一任务一', key: '2' },
        { title: '任务一', key: '3' },
      ]
    };
  }

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'task/fetchTaskTreeData',
    });
   const containerHeight = document.body.clientHeight - 152;
   document.getElementsByClassName("task-submit-container")[0].style.height = `${containerHeight}px`
  }

  onSelect = (selectedKeys,{selected,node}) => {

    console.log(node);
  };

  onExpand = () => {
    console.log('Trigger Expand');
  };

  saveCode = () => {
    const a = this.state.codeValue
    console.log(`${a}`)
  }

  submitCode = () => {
    console.log(this.state.codeValue)
  }

  dropDownMenu = () => {
    const menu = (
      <Menu>
        <Menu.Item>
          <a href="javascript:;" onClick={this.closeOtherTab}>关闭其他</a>
        </Menu.Item>
        <Menu.Item>
          <a href="javascript:;" onClick={this.closeAllTab}>关闭所有</a>
        </Menu.Item>
      </Menu>
    );
    return (
      <Dropdown overlay={menu} className={styles.dropDownMenu}>
        <a className="ant-dropdown-link" href="#">
          <Icon type="ellipsis" theme="outlined" className={styles.dropDownMenuIcon} />
        </a>
      </Dropdown>
    )
  }

  LeafIcon = () => <span className={styles.leafDot} />

  TreeNodeList = data =>
  // data ? (
    data.map(node => {
      if (!node.type) {
        return (
          <TreeNode
            icon={({ expanded }) => <Icon component={expanded ? OpenFolder : Folder} />}
            title={node.name}
            key={node.id}
            selectable={false}
          >
            {node.children && this.TreeNodeList(node.children)}
          </TreeNode>
        );
      }
      return <TreeNode key={node.id} icon={this.LeafIcon()} title={node.name} isLeaf />;
    })
  // ) : (
    // <TreeNode title="加载中" icon={<Spin />} key={-1} isLeaf />
  ;

  onTabChange = () => {

  }

  render() {
    const {
      treeDataLoading,
      taskTreeData,
    } = this.props;
    const { initCode } = this.state;
    
    return (
      <PageHeaderWrapper>
        <Row className="task-submit-container" style={{background: "#FFF"}}>
          <Col span={4} style={{height: "100%",background: "#fff",borderRight: "1px solid #E7E7E7"}}>
            {treeDataLoading ? <Spin /> : 
            <Tree
              onSelect={this.onSelect}
              onExpand={this.onExpand}
              showIcon
            >{this.TreeNodeList(taskTreeData)}
            </Tree>}
          </Col>
          <Col span={20} style={{height: "100%",background: "#FAFAFA",position: "relative"}}>
            <Row className={styles.tabWrap}>
              <Tabs
                type="card"
                onChange={this.onTabChange}
              >
                {this.state.panes.map(pane =>
                  <TabPane
                    tab={<span className={styles.tab}>
                      {pane.title}
                      <Icon
                        className={styles['close-icon']}
                        type="close-circle"
                        theme="outlined"
                      />
                    </span>}
                    key={pane.key}
                  />)}
              </Tabs>
              {this.dropDownMenu()}
            </Row>
            <Row style={{height: "calc(100% - 48px)"}}>
              <CodeMirror
                value={initCode}
                options={{
                      theme: 'neo',
                      lineNumbers: true
                    }}
                onChange={(editor, data, value) => {
                      this.setState({codeValue: value})
                    }}
              />
            </Row>
          </Col>  
        </Row>
        <Row className={styles["btn-wrap"]}>
          <Col span={24} className={styles.btnCol}>
            <Button type="primary" onClick={this.saveCode} className={styles.btn}>保存</Button>
            <Button onClick={this.submitCode} className={styles.btn}>提交</Button>
            <Button onClick={() => router.push('/jobPlan?jobId=1')} className={styles.btn2}>查看依赖</Button>
          </Col>
        </Row>
      </PageHeaderWrapper>
    );
  }
}
export default TaskSubmit;
