<script setup lang="ts">
import {ref} from "vue";

const activeMenu = ref('/naver')

</script>
<template>
  <el-header>
    <el-menu :default-active="activeMenu" mode="horizontal" router>
      <el-menu-item index="/naver">네이버</el-menu-item>
      <el-menu-item index="/kakao">카카오</el-menu-item>
    </el-menu>
    <div class="h-6"/>
  </el-header>
</template>
<style scoped>

.el-header {
  clear: both;
  position: relative;
  width: 980px;
  min-height: 100%;
  padding: 8px;
  margin: 0 auto;
}

.el-menu {
  width: 694px;
}
.el-menu--horizontal{
  display: block;
}
</style>