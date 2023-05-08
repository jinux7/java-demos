import * as VueRouter from 'vue-router';

const routes = [
  // { path: '/test', component: import('@/views/test/Test.vue') },
  { path: '/login', component: import('@/views/login/login.vue') },
  { 
    path: '/',
    redirect: '/user',
    component: import('@/views/layout/layout.vue'),
    children: [
      {
        path: 'user',
        name: 'user',
        component: import('@/views/user/User.vue')
      }
    ]
  },
  {
    path: '/404',
    name: '404',
    component: import('@/views/layout/404.vue')
  },
  { 
    path: '/:pathMatch(.*)',
    redirect: '/404'
  },
]

const router = VueRouter.createRouter({
  // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
  // history: VueRouter.createWebHashHistory(),
  history: VueRouter.createWebHistory(),
  routes, // `routes: routes` 的缩写
});

export default router;