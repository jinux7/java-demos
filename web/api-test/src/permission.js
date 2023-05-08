import router from "./router";
import { getToken } from "./utils/cookie";
// import { Message } from 'element-plus';

const whiteList = ['/login', '/register'];

router.beforeEach((to, from, next)=> {
  if(getToken()) {
    if(to.path==='/login') {
      next({path: '/'});
    }else {
      next();
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