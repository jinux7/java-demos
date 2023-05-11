import store from '@/utils/store.js';
export const getRoute = ()=> {
  return new Promise(async (resolve, reject)=> {
    const routes = [
      { 
        path: '/nava/',
        meta: {
          title: 'nava'
        },
        show: true,
        component: import('@/views/layout/layout.vue'),
        children: [
          {
            path: 'a1',
            name: 'a1',
            show: true,
            meta: {
              title: 'a1'
            },
            component: import('@/views/layout/content.vue')
          },
          {
            path: 'a2',
            name: 'a2',
            show: true,
            meta: {
              title: 'a2'
            },
            component: import('@/views/layout/content.vue')
          }
        ]
      },
      { 
        path: '/navb/',
        meta: {
          title: 'navb'
        },
        show: true,
        component: import('@/views/layout/layout.vue'),
        children: [
          {
            path: 'b1',
            name: 'b1',
            show: true,
            meta: {
              title: 'b1'
            },
            component: import('@/views/layout/content.vue')
          },
          {
            path: 'b2',
            name: 'b2',
            show: true,
            meta: {
              title: 'b2'
            },
            component: import('@/views/layout/content.vue')
          }
        ]
      }
    ];
    // await sleep(3);
    generateNav(routes);
    resolve(routes);
  });
}

export const generateNav = (routes)=> {
  store.navList = routes;
  console.log(store.navList, '=========');
}

export const sleep = timeout=> {
  return new Promise(resolve=> {
    setTimeout(()=> {
      resolve();
    }, timeout * 1000);
  });
}