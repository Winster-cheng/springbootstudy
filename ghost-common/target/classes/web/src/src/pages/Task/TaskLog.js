import React, {Component} from 'react';
import {Icon} from 'antd';
import './TaskLog.less';
import {Controlled as CodeMirror} from 'react-codemirror2';
// import { isEqual } from "lodash";
import logdown from '../../assets/icon_log_down.svg';
import logup from '../../assets/icon_log_top.svg';
import { statuslogo } from '@/utils/constant';

require ('codemirror/mode/javascript/javascript');
require ('codemirror/lib/codemirror.css');
require ('codemirror/theme/neo.css');

class TaskLog extends Component {
  editor = null;

  constructor (props) {
    super (props);
    this.state = {
      activeIndex: 0,
    };
  }

  expand = isExpand => {
    this.props.expandLog && this.props.expandLog (isExpand);
  };

  render () {
    const {logs, expandLevel} = this.props;
    const {activeIndex} = this.state;
    const logContent = logs[activeIndex] ? logs[activeIndex].content : '';
    return (
      <div className="log-wrap">
        <h3 className="log-header">
          任务日志
          <Icon
            onClick={() => this.expand (true)}
            component={logup}
            style={{
              right: expandLevel ? '28px' : '8px',
              display: expandLevel != 2 ? 'inline-block' : 'none',
            }}
            className="log-header-icon log-header-icon_top"
          />
          <Icon
            onClick={() => this.expand (false)}
            component={logdown}
            style={{
              right: expandLevel == 1 || 2 ? '8px' : '28px',
              display: expandLevel ? 'inline-block' : 'none',
            }}
            className="log-header-icon log-header-icon_bottom"
          />
        </h3>
        <div className="log-content" style={{height: 'calc(100% - 32px)'}}>
          <div className="log-content-left">
            <ul>
              {logs.map ((log, index) => (
                <li
                  key={index}
                  onClick={() =>
                    this.setState ({
                      activeIndex: index,
                    })}
                >
                  <Icon
                    component={statuslogo[log.status.id]}
                    className="log-status"
                  />
                  {log.time}
                </li>
              ))}
            </ul>
          </div>
          <div className="log-content-right">
            <CodeMirror
              value={logContent}
              options={{
                theme: 'neo',
                readOnly: true,
              }}
              editorDidMount={editor => {
                this.editor = editor;
              }}
            />
          </div>
        </div>
      </div>
    );
  }
}
export default TaskLog;
