<script setup>
import Nav from '@/components/nav.vue';
</script>
<template>
  <div>
    <div class="wrap-nav">
      <Nav></Nav>
    </div>
    <div class="wrap-body">
      <header>
        header
      </header>
      <section>
        <router-view></router-view>
      </section>
    </div>
  </div>
</template>
<style lang="less" scoped>
.wrap-nav {
  width: 200px;
  height: 100vh;
  background-color: beige;
  float: left;
}
.wrap-body {
  margin-left: 200px;
  header {
    padding: 10px;
    background-color: gray;
  }
  section {
    padding: 10px 20px;
  }
}
</style>