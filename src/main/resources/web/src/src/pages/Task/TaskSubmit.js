import React, { Component } from 'react';
import { connect } from 'dva';
import router from 'umi/router';
import { Tree, Row, Col, Button } from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import {UnControlled as CodeMirror} from 'react-codemirror2'

require('codemirror/mode/javascript/javascript');
require('codemirror/lib/codemirror.css');
require('codemirror/theme/neo.css');

import styles from "./TaskSubmit.less"

const { DirectoryTree, TreeNode } = Tree;

@connect(({ task, loading }) => ({
  taskTreeData: task.treeData,
  loading: loading.models.task,
}))
class TaskSubmit extends Component {
  constructor(props, context) {
    super(props, context);
    this.state = {
      initCode: 'const a = 0;',
      codeValue: "",
      initResult: "提交成功"
    };
  }

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'task/fetchTaskTreeData',
    });
  }

  onSelect = () => {
    console.log('Trigger Select');
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

  render() {
    const {
      // loading,
      taskTreeData,
    } = this.props;
    const { initCode, initResult } = this.state;
    const TreeNodeList = data =>
      data ? (
        data.map(node => {
          if (node.children) {
            return (
              <TreeNode title={node.name} key={node.id}>
                {TreeNodeList(node.children)}
              </TreeNode>
            );
          }
          return <TreeNode title={node.name} key={node.id} isLeaf />;
        })
      ) : (
        <TreeNode title="加载中" key={-1} isLeaf />
      );
    return (
      <PageHeaderWrapper>
        <Row className={styles.container}>
          <Col span={4} className={styles.treeContainer}>
            <DirectoryTree
              multiple
              defaultExpandAll
              onSelect={this.onSelect}
              onExpand={this.onExpand}
            >
              {TreeNodeList(taskTreeData)}
            </DirectoryTree>
          </Col>
          <Col span={20} className={styles.codeContainer}>
            <Row>
              <Button onClick={this.saveCode}>保存</Button>
              <Button onClick={this.submitCode}>提交</Button>
              <Button onClick={() => router.push('/jobPlan?jobId=1')}>查看依赖</Button>
            </Row>
            <Row className={styles.codeRow}>
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
            <Row className={styles.resultRow}>
              <CodeMirror
                value={initResult}
                options={{
                  theme: 'neo',
                  lineNumbers: true,
                  readOnly: true
                }}
              />
            </Row>
          </Col>
        </Row>
      </PageHeaderWrapper>
    );
  }
}
export default TaskSubmit;
