<script setup>
import { ref } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
console.log(route.meta);
</script>

<template>
  <div>
    <h1>{{ route.meta.title }}</h1>
  </div>
</template>
<style scoped lang="less">

</style>
