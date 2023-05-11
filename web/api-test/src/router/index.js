import * as VueRouter from 'vue-router';

const routes = [
  // { path: '/test', component: import('@/views/test/Test.vue') },
  { 
    path: '/login',
    name: 'login',
    meta: {
      title: 'login'
    },
    show: false,
    component: import('@/views/login/login.vue') },
  { 
    path: '/',
    redirect: '/user',
    show: false,
    component: import('@/views/layout/layout.vue'),
    children: [
      {
        path: 'user',
        name: 'user',
        meta: {
          title: 'user'
        },
        component: import('@/views/user/User.vue')
      }
    ]
  },
  {
    path: '/404',
    name: '404',
    show: false,
    meta: {
      title: '404'
    },
    component: import('@/views/layout/404.vue')
  },
  // { 
  //   path: '/:pathMatch(.*)',
  //   redirect: '/404'
  // },
]

const router = VueRouter.createRouter({
  // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
  // history: VueRouter.createWebHashHistory(),
  history: VueRouter.createWebHistory(),
  routes, // `routes: routes` 的缩写
});

// router.addRoute({
//   path: '/404',
//   name: '404',
//   meta: {
//     title: '404'
//   },
//   component: import('@/views/layout/404.vue')
// });
// router.addRoute({ 
//   path: '/:pathMatch(.*)',
//   redirect: '/404'
// });
export default router;