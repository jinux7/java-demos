import store from '@/utils/store.js';
import { getMenu } from '../api';
const modules = import.meta.glob('../views/**/*.vue');

console.log(modules, '-------');

export const getRoute = ()=> {
  return new Promise(async (resolve, reject)=> {
    // const routes = [
    //   { 
    //     path: '/nava/',
    //     meta: {
    //       title: 'nava',
    //       icon: 'connection'
    //     },
    //     show: true,
    //     component: import('@/views/layout/layout.vue'),
    //     children: [
    //       {
    //         path: 'a1',
    //         name: 'a1',
    //         show: true,
    //         meta: {
    //           title: 'a1',
    //           icon: 'clock'
    //         },
    //         component: import('@/views/layout/content.vue')
    //       },
    //       {
    //         path: 'a2',
    //         name: 'a2',
    //         show: true,
    //         meta: {
    //           title: 'a2',
    //           icon: 'bell'
    //         },
    //         component: import('@/views/layout/content.vue')
    //       }
    //     ]
    //   },
    //   { 
    //     path: '/navb/',
    //     meta: {
    //       title: 'navb',
    //       icon: 'setting'
    //     },
    //     show: true,
    //     component: import('@/views/layout/layout.vue'),
    //     children: [
    //       {
    //         path: 'b1',
    //         name: 'b1',
    //         show: true,
    //         meta: {
    //           title: 'b1',
    //           icon: 'edit'
    //         },
    //         component: import('@/views/layout/content.vue')
    //       },
    //       {
    //         path: 'b2',
    //         name: 'b2',
    //         show: true,
    //         meta: {
    //           title: 'b2',
    //           icon: 'editPen'
    //         },
    //         component: import('@/views/layout/content.vue')
    //       }
    //     ]
    //   }
    // ];
    // await sleep(3);
    let res = await getMenu();
    let routes = [];
    arr2tree(res.data.result, 0, routes);
    generateRoute(routes);
    // console.log(routes, 998);
    generateNav(routes);
    resolve(routes);
  });
}

export const generateNav = (routes)=> {
  store.navList = routes;
  // console.log(store.navList, '=========');
}

export const sleep = timeout=> {
  return new Promise(resolve=> {
    setTimeout(()=> {
      resolve();
    }, timeout * 1000);
  });
}

export const arr2tree = (arr, parentId, tree=[])=> {
  // debugger;
  arr.forEach(item=> {
    if(item.parentId===parentId) {
      tree.push(item);
    }
  });
  tree.forEach(item=> {
    if(!item.children) {
      item.children = [];
    }
    arr2tree(arr, item.id, item.children);
  });
}

const generateRoute= (tree)=> {
  tree.forEach(item=> {
    console.log(item.component);
    item.component = loadView(item.component);
    item.meta = {};
    item.meta.title = item.title;
    item.meta.icon = item.icon;
    if(item.children&&item.children.length>0) {
      generateRoute(item.children);
    }
  });
}
// vue3+vite动态路由的写法，需要modules
const loadView = view=> {
  let res;
  for(let path in modules) {
    if(path.indexOf(view)>-1) {
      res = ()=> modules[path]();
    }
  }
  return res;
}