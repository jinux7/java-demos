<script setup>
import store from '@/utils/store.js';
import { useRouter } from 'vue-router';

const router = useRouter();
const handleOpen = ()=> {
  console.log('handleOpen');
}
const handleClose = ()=> {
  console.log('handleClose');
}
const onNavClick = name=> {
  router.push({ name });
}
</script>
<template>
  <div>
    <el-menu
        @open="handleOpen"
        @close="handleClose"
      >
        <div v-for="(item, index) in store.navList" :key="index">
          <el-sub-menu v-if="item.children&&item.children.length>0" :index="item.path">
            <template #title>
              <el-icon><location /></el-icon>
              <span>{{ item.meta.title }}</span>
            </template>
            <el-menu-item v-for="(ele, idx) in item.children" :key="idx" :index="item.path+ele.path" 
              @click="onNavClick(ele.name)">
              {{ ele.meta.title }}
            </el-menu-item>
          </el-sub-menu>
        </div>
      </el-menu>
  </div>
</template>