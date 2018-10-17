import React, { Component } from 'react';
import { connect } from 'dva';
import { Tree, Row, Col, Button } from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import { Controlled as CodeMirror } from 'react-codemirror2';
import './TaskSubmit.less';
// require('codemirror/lib/codemirror.css');

const { DirectoryTree, TreeNode } = Tree;

@connect(({ task, loading }) => ({
  taskTreeData: task.treeData,
  loading: loading.models.task,
}))
class TaskSubmit extends Component {
  constructor(props, context) {
    super(props, context);
    this.state = {
      code: '',
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

  render() {
    const {
      // loading,
      taskTreeData,
    } = this.props;
    const { code } = this.state;
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
    const options = {
      mode: 'xml',
      theme: 'material',
      lineNumbers: true,
    };
    return (
      <PageHeaderWrapper>
        <Row>
          <Col span={4}>
            <DirectoryTree
              multiple
              defaultExpandAll
              onSelect={this.onSelect}
              onExpand={this.onExpand}
            >
              {TreeNodeList(taskTreeData)}
            </DirectoryTree>
          </Col>
          <Col span={20}>
            <Row>
              <Button>保存</Button>
              <Button>提交</Button>
              <Button>查看依赖</Button>
            </Row>
            <Row>
              <CodeMirror
                value={code}
                options={options}
                onBeforeChange={(editor, data, value) => {
                  {
                    /* this.setState({value}); */
                  }
                }}
                onChange={(editor, data, value) => {}}
              />
            </Row>
          </Col>
        </Row>
      </PageHeaderWrapper>
    );
  }
}
export default TaskSubmit;
