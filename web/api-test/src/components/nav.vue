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
        :unique-opened="true"
        @open="handleOpen"
        @close="handleClose"
      >
        <div v-for="(item, index) in store.navList" :key="index">
          <el-sub-menu v-if="item.children&&item.children.length>1&&item.show==='1'" :index="item.path">
            <template #title>
              <el-icon><component :is="item.meta.icon" /></el-icon>
              <span>{{ item.meta.title }}</span>
            </template>
            <div v-for="(ele, idx) in item.children" :key="idx">
              <el-menu-item :index="item.path+ele.path" 
                @click="onNavClick(ele.name)"
                v-if="ele.show==='1'">
                <el-icon><component :is="ele.meta.icon" /></el-icon>
                <span>
                  {{ ele.meta.title }}
                </span>
              </el-menu-item>
            </div>
          </el-sub-menu>
          <el-menu-item v-if="item.children&&item.children.length===1&&item.show==='1'"
            @click="onNavClick(item.children[0].name)">
            <el-icon><component :is="item.children[0].meta.icon" /></el-icon>
            <span>{{ item.children[0].meta.title }}</span>
          </el-menu-item>
        </div>
      </el-menu>
  </div>
</template>