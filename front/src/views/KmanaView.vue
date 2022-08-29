<script lang="ts" setup>
import {ref} from 'vue'
import type {TabsPaneContext} from 'element-plus'

const activeName = ref('mon')

const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab.props.name)
}
</script>

<template>
  카카오
</template>
<style>

</style>