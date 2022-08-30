<script lang="ts" setup>

import {defineProps} from 'vue';

const props = defineProps({
  href: {
    type: [String, String],
    require: true,
  },
});
console.log(props)
</script>

<template>
  네이버디테일뷰
</template>
<style>

</style>