import React, { PureComponent } from 'react';
import { Table } from 'antd';
import styles from './index.less';

export default class GeneralTable extends PureComponent {
  // constructor(props) {
  //   super(props);
  // }

  handleTableChange = (pagination, filters, sorter) => {
    this.props.onChange(pagination, filters, sorter);
  };

  render() {
    const {
      data: {list, ...pagination},
      loading,
      columns,
      rowKey,
      scroll,
      rowSelection,
      size = 'default',
    } = this.props;

    const paginationProps = {
      showSizeChanger: true,
      showQuickJumper: true,
      defaultCurrent: 1,
      defaultPageSize: 20,
      showTotal: total => `共${total}条`,
      ...pagination,
    };
    console.log(list)
    return (
      <div className={styles.generalTable}>
        <Table
          bordered
          size={size}
          rowSelection={rowSelection}
          scroll={scroll}
          loading={loading}
          rowKey={rowKey || 'id'}
          dataSource={list}
          columns={columns}
          pagination={paginationProps}
          onChange={this.handleTableChange}
        />
      </div>
    );
  }
}
