<script setup lang="ts"></script>
<template>
  <el-main class="daymenu">
    <el-row>
      <el-col>
        <RouterView />
      </el-col>
    </el-row>
  </el-main>
</template>
<style scoped>
.daymenu {
  height: calc(100vh - 60px);
}
</style>