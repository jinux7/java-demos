import { createApp } from 'vue';
const store = createApp({
  data() {
    return {
      bAddRoute: false,
      navList: []
    }
  }
});
const data = store._component.data();
export default data;