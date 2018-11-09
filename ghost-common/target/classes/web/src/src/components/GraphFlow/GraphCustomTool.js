import React from 'react';

export default isTaskInstance => GraphFlowComponent => props => (
  <GraphFlowComponent {...props} isTaskInstance={isTaskInstance} />
);
