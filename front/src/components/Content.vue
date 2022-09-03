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
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.el-main::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}
</style>