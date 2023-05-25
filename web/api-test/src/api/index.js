import request from '@/utils/request';

// 登录
export const login = (data)=> {
  return request.post('login', data);
}
// 获取路由menu
export const getMenu = data=> {
  return request.get('getmenu');
}

// user 获取列表
export const getUserList = (data)=> {
  return request.get('/user/list', data);
}
// user 获取详情
export const getUserById = id=> {
  return request.get('/user/' + id);
}
// user 删除
export const delUserById = id=> {
  return request.get('/user/del/' + id);
}
// user 添加
export const addUser= data=> {
  return request.post('/user/add', data);
}
// user 修改
export const updateUser= data=> {
  return request.post('/user/update', data);
}