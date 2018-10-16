export default [
  {
    path: '/',
    component: '../layouts/BasicLayout',
    Routes: ['src/pages/Authorized'],
    authority: ['admin', 'user'],
    routes: [
      {
        path: '/taskSubmit',
        name: 'taskSubmit',
        icon: 'dashboard',
        component: './Task/TaskSubmit',
      },
      {
        path: '/jobPlan',
        name: 'jobPlan',
        icon: 'dashboard',
        component: './Job/jobPlan',
      },
      {
        path: '/taskInstance',
        name: 'taskInstance',
        icon: 'dashboard',
        component: './Task/taskInstance',
      },
    ],
  },
];
