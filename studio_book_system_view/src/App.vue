<template>
  <div id="app">
    <!--    <div id="nav">-->
    <!--      <router-link to="/">Home</router-link> |-->
    <!--      <router-link to="/login">login</router-link>-->
    <!--    </div>-->

    <router-view />
  </div>
</template>
<script>
export default {
  data() {
    return {
      locale: "zhCN"
    };
  },
  mounted() {
    // this.$router.push("/login")
    if (sessionStorage.getItem("UserLoginName")) {
      console.log("sessionStorage.getItem true");
    } else {
      console.log("sessionStorage.getItem false");
      this.$router.push("/login");
    }
  }
};
</script>
<style>
/*#app {*/
/*  font-family: Avenir, Helvetica, Arial, sans-serif;*/
/*  -webkit-font-smoothing: antialiased;*/
/*  -moz-osx-font-smoothing: grayscale;*/
/*  text-align: center;*/
/*  color: #2c3e50;*/
/*}*/

/*#nav {*/
/*  padding: 30px;*/
/*}*/

/*#nav a {*/
/*  font-weight: bold;*/
/*  color: #2c3e50;*/
/*}*/

/*#nav a.router-link-exact-active {*/
/*  color: #42b983;*/
/*}*/
</style>
