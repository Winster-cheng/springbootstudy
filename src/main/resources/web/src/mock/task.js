export default {
  'GET /task/getTreeData': (req, res) =>
    res.json([
      {
        name: 'parent',
        id: 1,
        children: [
          {
            name: 'children1',
            id: 2,
            children: [{ name: 'test1', id: 10 }],
          },
          {
            name: 'children2',
            id: 4,
          },
          {
            name: 'children3',
            id: 3,
          },
        ],
      },
      {
        name: 'parent1',
        id: 5,
        children: [
          {
            name: 'children1',
            id: 6,
          },
        ],
      },
      {
        name: 'parent1',
        id: 7,
      },
    ]),
};
