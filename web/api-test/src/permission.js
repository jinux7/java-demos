import router from "./router";
import { getToken } from "./utils/cookie";
import { getRoute } from "./utils/util";
import store from '@/utils/store.js';
// import { Message } from 'element-plus';

const whiteList = ['/login', '/register'];

router.beforeEach(async (to, from, next)=> {
  if(getToken()) {
    if(to.path==='/login') {
      next({path: '/'});
    }else {
      if(!store.bAddRoute) {
        store.bAddRoute = true;
        const fetchRoute = await getRoute();
        fetchRoute.forEach(r=> {
          router.addRoute(r);
        });
        router.addRoute({ 
          path: '/:pathMatch(.*)',
          redirect: '/404'
        });
        // console.log(router.getRoutes(), '--------');
        next({...to, replace:true});
      }else {
        next();
      }
    }
  }else {
    if(whiteList.indexOf(to.path) !== -1) {
      next();
    }else {
      next(`/login?redirect=${to.fullPath}`);
    }
  }
});

router.afterEach(() => {

});