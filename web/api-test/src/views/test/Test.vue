<script setup>
import { ref, reactive, getCurrentInstance, onMounted } from 'vue'
import axios from 'axios'
const baseUrl = 'http://localhost:8888';


defineProps({
  msg: String,
})

const total = ref(0);
const pageParam = reactive({
  pageNum: 1,
  pageSize: 10
});
const self = getCurrentInstance();
const dialogType = ref('add');
const dialogFormVisible = ref(false);
const currentRowId = ref(undefined);
const form = reactive({
  name: '',
  sexy: null,
  age: null,
  job: '',
  hobby: '',
});
const tableData = ref([]);

onMounted(()=> {
  getList();
});

const getUserById = id=> {
  return axios.get(baseUrl + '/user/' + id);
}

const getList = ev=> {
  // pageParam.pageNum = pageNum || 1;
  axios.get(baseUrl + '/user/list', {
    params: pageParam
  }).then(res=> {
    tableData.value = res.data.result.list;
    total.value = res.data.result.total;
    console.log(res.data);
  });
}

const onAddUser = ev=> {
  dialogType.value = 'add';
  dialogFormVisible.value = true;
  form.name = '';
  form.sexy = null;
  form.age = null;
  form.job = '';
  form.hobby = '';
}
// table的查看按钮
const readHandler = id=> {
  dialogType.value = 'read';
  dialogFormVisible.value = true;
  getUserById(id).then(res=> {
    const da = res.data.result;
    form.name = da.name;
    form.sexy = da.sexy;
    form.age = da.age;
    form.job = da.job;
    form.hobby = da.hobby;
  });
}
// table的编辑按钮
const editHandler = row=> {
  dialogType.value = 'edit';
  dialogFormVisible.value = true;
  currentRowId.value = row.id;
  form.name = row.name;
  form.sexy = row.sexy;
  form.age = row.age;
  form.job = row.job;
  form.hobby = row.hobby;
}
// table的删除按钮
const delHandler = id=> {
  axios.get(baseUrl + '/user/del/' + id).then(res=> {
    if(res.data.status==200) {
      self.appContext.config.globalProperties.$message.success('删除成功');
      getList();
    }else {
      self.appContext.config.globalProperties.$message.success('删除失败');
    }
    
  });
}
// 弹出框的确认按钮回调
const onDialogHandler = ev=> {
  // console.log();
  // return ;
  dialogFormVisible.value = false;
  let apiName = dialogType.value==='add'?'add':'update';
  let successMsg = dialogType.value==='add'?'添加成功':'修改成功';
  form.id = dialogType.value==='add'?undefined:currentRowId;
  axios.post(baseUrl + '/user/' + apiName, form).then(res=> {
    if(res.data.status==200) {
      self.appContext.config.globalProperties.$message.success(successMsg);
      getList();
    }else {
      self.appContext.config.globalProperties.$message.error(res.data.result);
    }
    
  }).catch(error=> {
    self.appContext.config.globalProperties.$message.error(error);
  });
}
</script>

<template>
  <div>
    <div>
      <el-button type="primary" @click="getList">获取list数据</el-button>
      <el-button type="primary" @click="onAddUser">添加user</el-button>
    </div>
    <div class="table">
      <el-table :data="tableData" :border="true" style="width: 100%">
        <el-table-column label="序号" width="180">
          <template #default="scope">
            <span>{{ (pageParam.pageNum - 1) * pageParam.pageSize + scope.$index + 1 }}</span>
          </template>
        </el-table-column>
        <el-table-column label="姓名" width="180" prop="name" />
        <el-table-column label="性别" width="180">
          <template #default="scope">
            <span v-if="scope.row.sexy=='1'">男</span>
            <span v-else>女</span>
          </template>
        </el-table-column>
        <el-table-column label="年龄" width="180" prop="age" />
        <el-table-column label="职业" width="180" prop="job" />
        <el-table-column label="爱好" width="180" prop="hobby" />
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button 
              size="small" 
              @click="readHandler(scope.row.id)"
              >查看</el-button>
            <el-button 
              size="small" 
              @click="editHandler(scope.row)"
              >编辑</el-button>
            <el-popconfirm @confirm="delHandler(scope.row.id)" title="确定删除操作吗？" width="200">
              <template #reference>
                <el-button
                  size="small"
                  type="danger"
                  >删除</el-button> 
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination background 
        style="float:right;margin-top:10px;"
        layout="prev, pager, next"
        @current-change="getList"
        v-model:current-page="pageParam.pageNum" :total="total" />
    </div>
    <el-dialog v-model="dialogFormVisible" title="添加user" >
      <el-form :model="form" :disabled="dialogType==='read'" label-width="120px">
        <el-form-item label="姓名：">
          <el-input v-model="form.name" placeholder="请输入"  autocomplete="off" style="width:100%;"/>
        </el-form-item>
        <el-form-item label="性别：">
          <el-select v-model="form.sexy" placeholder="请选择" style="width:100%;">
            <el-option label="男" value="1" />
            <el-option label="女" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="年龄：">
          <el-input v-model.number="form.age" placeholder="请输入" style="width:100%;"/>
        </el-form-item>
        <el-form-item label="职业：">
          <el-input v-model="form.job" placeholder="请输入"  autocomplete="off" style="width:100%;"/>
        </el-form-item>
        <el-form-item label="爱好：">
          <el-input v-model="form.hobby" placeholder="请输入"  type="textarea" style="width:100%;"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button v-show="dialogType!=='read'" type="primary" @click="onDialogHandler">
            确定
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>
<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
.table {
  margin-top: 20px;
}
</style>
