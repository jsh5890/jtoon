<script setup lang="ts"></script>
<template>
  <el-header>
    <el-menu mode="horizontal" router>
      <el-menu-item index="/naver">네이버</el-menu-item>
      <el-menu-item index="/kakao">카카오</el-menu-item>
    </el-menu>
    <div class="h-6"/>
  </el-header>
</template>
<style scoped>
.header {
  padding: 0;
  height: 60px;
}
</style>