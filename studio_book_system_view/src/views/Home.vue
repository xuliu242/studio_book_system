<template>
  <div>
    <el-calendar v-model="value">
    </el-calendar>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data() {
    return {
      value: new Date()
    }
  },
  mounted () {
    this.$emit('getMessage', ["首页","首页"]);
  },
  methods:{

  }
}
</script>

<style scoped>

</style>
