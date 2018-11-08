import React, {Component} from 'react';
import {connect} from 'dva';
import router from 'umi/router';
import {
  Tree,
  Row,
  Col,
  Button,
  Spin,
  Icon,
  Tabs,
  Menu,
  Dropdown,
  message,
  Modal,
} from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import {Controlled as CodeMirror} from 'react-codemirror2';
import Folder from '../../assets/icon_folder.svg';
import OpenFolder from '../../assets/icon_folder_open.svg';
import DropdownIcon from '../../assets/icon_dropdown.svg';

require ('codemirror/mode/javascript/javascript');
require ('codemirror/lib/codemirror.css');
require ('codemirror/theme/neo.css');

import styles from './TaskSubmit.less';

const {TreeNode} = Tree;
const {TabPane} = Tabs;
const {confirm} = Modal;
@connect (({task, loading}) => ({
  taskTreeData: task.treeData,
  treeDataLoading: loading.effects['task/fetchTaskTreeData'],
}))
class TaskSubmit extends Component {
  editor = null;

  constructor (props, context) {
    super (props, context);
    this.state = {
      activeKey: 0,
      panes: [],
      currentCodeContent: '',
    };
  }

  componentDidMount () {
    const {dispatch} = this.props;
    dispatch ({
      type: 'task/fetchTaskTreeData',
    });
    const containerHeight = document.body.clientHeight - 152;
    document.getElementsByClassName (
      'task-submit-container'
    )[0].style.height = `${containerHeight}px`;
  }

  onSelect = (selectedKeys, {node}) => {
    const {title, shelltype, nodeid} = node.props;
    const that = this;
    const {panes, activeKey, currentCodeContent} = that.state;
    panes.forEach (pane => {
      if (pane.key == activeKey) {
        pane.content = currentCodeContent;
      }
    });
    if (!this.state.panes.some (pane => pane.key == nodeid)) {
      this.props
        .dispatch ({
          type: 'task/fetchCodeContent',
          payload: nodeid,
        })
        .then (content => {
          if (content) {
            const newPane = {
              title,
              key: nodeid,
              content,
            };
            panes.push (newPane);
            // 新增panel
            that.setState (
              {
                panes,
                activeKey: nodeid,
                currentCodeContent: content,
              },
              () => {
                that.editor && that.editor.focus ();
              }
            );
          }
        });
    } else {
      // 显示当前id对应的panel
      this.setState ({
        panes,
        activeKey: nodeid,
        currentCodeContent: this.state.panes.filter (
          pane => pane.key == nodeid
        )[0].content,
      });
    }
  };

  removePane = (e, targetKey) => {
    e.stopPropagation ();
    let {activeKey, currentCodeContent, panes} = this.state;
    let lastIndex;
    panes.forEach ((pane, i) => {
      if (pane.key == targetKey) {
        lastIndex = i ? i - 1 : i + 1;
      }
    });
    const panesFiltered = panes.filter (pane => pane.key !== targetKey);
    if (activeKey == targetKey && panes[lastIndex]) {
      const {key, content} = panes[lastIndex];
      activeKey = key;
      currentCodeContent = content;
    }
    this.setState ({panes: panesFiltered, activeKey, currentCodeContent});
  };

  saveCode = isSubmit => {
    const {activeKey, currentCodeContent: content} = this.state;
    const {dispatch} = this.props;
    dispatch ({
      type: `task/${isSubmit ? 'submit' : 'save'}TaskContent`,
      payload: {
        fileId: activeKey,
        content,
      },
    }).then (({result, message:msg}) => {
      if (result) {
        message.success ('保存成功');
      } else {
        message.error (msg);
      }
    });
  };

  submitCode = () => {
    const that = this;
    confirm ({
      title: '确认提交',
      content: '你确定要提交这个任务吗，如果确定的话，请点击确定按钮。',
      onOk () {
        that.saveCode (true);
      },
      onCancel () {},
    });
  };

  closeTab = closeAll => {
    const {panes, activeKey} = this.state;
    this.setState ({
      panes: closeAll ? [] : panes.filter (pane => pane.key == activeKey),
    });
  };

  dropDownMenu = () => {
    const menu = (
      <Menu>
        <Menu.Item>
          <a href="javascript:;" onClick={() => this.closeTab ()}>关闭其他</a>
        </Menu.Item>
        <Menu.Item>
          <a href="javascript:;" onClick={() => this.closeTab (true)}>关闭所有</a>
        </Menu.Item>
      </Menu>
    );
    return (
      <Dropdown overlay={menu} className={styles.dropDownMenu}>
        <a className="ant-dropdown-link" href="#">
          <Icon component={DropdownIcon} className={styles.dropDownMenuIcon} />
        </a>
      </Dropdown>
    );
  };

  LeafIcon = () => <span className={styles.leafDot} />;

  TreeNodeList = data =>
    data.map (node => {
      if (!node.type) {
        return (
          <TreeNode
            icon={({expanded}) => (
              <Icon component={expanded ? OpenFolder : Folder} />
            )}
            title={node.name}
            key={node.id}
            selectable={false}
          >
            {node.children && this.TreeNodeList (node.children)}
          </TreeNode>
        );
      }
      return (
        <TreeNode
          key={node.id}
          nodeid={node.id}
          icon={this.LeafIcon ()}
          shelltype={node.shelltype}
          title={node.name}
          isLeaf
        />
      );
    });

  onTabChange = sourceKey => {
    let targetPane = {};
    const {panes, activeKey, currentCodeContent} = this.state;
    panes.forEach (pane => {
      if (pane.key == activeKey) {
        pane.content = currentCodeContent;
      }
      if (pane.key == sourceKey) {
        targetPane = pane;
      }
    });
    const {content = ''} = targetPane;
    this.setState ({
      panes,
      currentCodeContent: content,
      activeKey: sourceKey,
    });
  };

  render () {
    const {treeDataLoading, taskTreeData} = this.props;
    const rightSectionDisplay = this.state.panes.length > 0 ? 'block' : 'none';
    const editorDisplay = this.state.panes.length > 0 ? 'visible' : 'hidden';
    return (
      <PageHeaderWrapper>
        <Row className="task-submit-container" style={{background: '#FFF'}}>
          <Col
            span={6}
            style={{
              height: '100%',
              background: '#fff',
              borderRight: '1px solid #E7E7E7',
              overflow: 'auto',
            }}
          >
            {treeDataLoading
              ? <Spin />
              : <Tree
                onSelect={this.onSelect}
                showIcon
                selectedKeys={[this.state.activeKey.toString ()]}
              >
                {this.TreeNodeList (taskTreeData)}
              </Tree>}
          </Col>
          <Col
            span={18}
            style={{
              height: '100%',
              background: '#FAFAFA',
              position: 'relative',
            }}
          >
            <Row
              style={{display: rightSectionDisplay}}
              className={styles.tabWrap}
            >
              <Tabs
                type="card"
                onChange={this.onTabChange}
                activeKey={this.state.activeKey.toString ()}
              >
                {this.state.panes.map (pane => (
                  <TabPane
                    key={pane.key}
                    tab={
                      <span className={styles.tab}>
                        {pane.title}
                        <Icon
                          className={styles['close-icon']}
                          type="close-circle"
                          theme="outlined"
                          onClick={e => this.removePane (e, pane.key)}
                        />
                      </span>
                    }
                  />
                ))}
              </Tabs>
              {this.dropDownMenu ()}
            </Row>
            <Row
              style={{visibility: editorDisplay, height: 'calc(100% - 48px)'}}
            >
              <CodeMirror
                value={this.state.currentCodeContent}
                options={{
                  theme: 'neo',
                  lineNumbers: true,
                }}
                editorDidMount={editor => {
                  this.editor = editor;
                }}
                onBeforeChange={(editor, data, value) => {
                  this.setState ({currentCodeContent: value});
                }}
              />
            </Row>
          </Col>
        </Row>
        <Row
          style={{display: rightSectionDisplay}}
          className={styles['btn-wrap']}
        >
          <Col span={24} className={styles.btnCol}>
            <Button
              type="primary"
              onClick={() => this.saveCode ()}
              className={styles.btn}
            >
              保存
            </Button>
            <Button onClick={this.submitCode} className={styles.btn}>提交</Button>
            <Button
              onClick={() => router.push ('/jobPlan')}
              className={styles.btn2}
            >
              查看依赖
            </Button>
          </Col>
        </Row>
      </PageHeaderWrapper>
    );
  }
}
export default TaskSubmit;
