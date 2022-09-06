<script setup lang="ts"></script>
<template>
  <el-main>
    <el-row>
      <el-col>
        <RouterView />
      </el-col>
    </el-row>
  </el-main>
</template>
<style scoped>
.el-main {
  overflow-y: hidden;
}
/*.el-main::-webkit-scrollbar {*/
/*  display: none; !* Chrome, Safari, Opera*!*/
/*}*/
</style>