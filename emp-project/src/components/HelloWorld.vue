<template>
  <div class="main">
  <div class="about">
    <h3>员工列表</h3>
    <el-form :model="filters" inline class="filters-sty" @keyup.enter.native="getAllData">
      <!-- <el-form-item label="关键词：">
        <el-input v-model="filters.keyWords" placeholder="请输入关键词"></el-input>
      </el-form-item> -->
      <el-form-item label="员工姓名：">
        <el-input v-model="filters.empName" placeholder="请输入关员工姓名"></el-input>
      </el-form-item>
      <el-form-item label="部门编号：">
        <el-input v-model="filters.deptId" placeholder="请输入部门编号"></el-input>
      </el-form-item>
    </el-form>
    <el-button type="success" class="add-sty" @click="openAdd">添加员工</el-button>
    <el-button type="primary" class="add-sty" @click="getAllData">搜索</el-button>
    <el-table :data="tableData" border>
    <el-table-column prop="id" label="员工编号" align="center"></el-table-column>
    <el-table-column prop="empName" label="员工姓名" align="center"></el-table-column>
    <el-table-column prop="age" label="年龄" align="center"></el-table-column>
    <el-table-column prop="sex" :formatter="sexFilter" label="性别" align="center"></el-table-column>
    <el-table-column prop="deptId" label="部门编号" align="center"></el-table-column>
    <el-table-column label="操作栏" align="center">
      <template slot-scope="scope">
        <el-button type="primary" @click="editOpen(scope.row)">编辑</el-button>
        <el-button type="danger" @click="delteEmp(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  </div>
  <el-pagination
      class="page-sty"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[5, 10, 30, 50, 100]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
    <!-- 弹框区域 -->
    <el-dialog :title="empTitle" class="dialog-sty" :visible.sync="dialogFormVisible" width="600px">
    <el-form ref="empForm" :model="empForm" label-width="100px" :rules="rules">
      <el-form-item label="员工ID：" prop="id">
      <el-input :disabled="empTitle=='添加员工'?false:true" v-model="empForm.id" placeholder="请输入"></el-input>
    </el-form-item>
    <el-form-item label="员工姓名：" prop="empName">
      <el-input v-model="empForm.empName" placeholder="请输入"></el-input>
    </el-form-item>
    <el-form-item label="员工年龄：" prop="age">
      <el-input v-model="empForm.age" placeholder="请输入"></el-input>
    </el-form-item>
    <el-form-item label="性别：" prop="sex">
      <el-radio-group v-model="empForm.sex" style="margin-left:-350px;">
        <el-radio :label="1">男</el-radio>
        <el-radio :label="0">女</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="部门编号：" prop="deptId">
      <el-input v-model="empForm.deptId" placeholder="请输入"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button v-if="empTitle=='添加员工'" type="primary" @click="addEmp">确 定</el-button>
    <el-button v-else type="primary" @click="editEmp">确 定</el-button>
    <el-button @click="dialogFormVisible = false">取 消</el-button>
  </div>
</el-dialog>
  </div>
</template>

<script>
import { getEmp } from "@/api/emp"
export default {
  name: 'HelloWorld',
  data () {
    return {
      empTitle:"添加员工",
      empForm:{
        id:"",
        empName:"",
        age:"",
        sex:"",
        deptId:""
      },
      filters:{
        keyWords:"",
        empName:"",
        deptId:""
      },
      rules:{
        id: [{ required: true, message: '请输入', trigger: 'blur' }],
        empName: [{ required: true, message: '请输入', trigger: 'blur' }],
        age: [{ required: true, message: '请输入', trigger: 'blur' }],
        sex: [{ required: true, message: '请选择', trigger: 'blur' }],
        deptId: [{ required: true, message: '请输入', trigger: 'blur' }],
      },
      dialogFormVisible:false,
      total:0,
      currentPage:1,
      pageSize:5,
      tableData: []
    }
  },
  methods: {
    openAdd(){
      this.empForm = {}
      this.dialogFormVisible=true
      this.empTitle='添加员工'
    },
    editEmp(){
      this.$refs["empForm"].validate(val=>{
        if(val){
          let params = {
           id:this.empForm.id,
           empName:this.empForm.empName,
           age:this.empForm.age,
           sex:this.empForm.sex,
           deptId:this.empForm.deptId
          }
         this.axios.post("/api/editEmp",params).then(res=>{
           console.log(res,"请求结果！")
           if(res.data.code == 1000){
             this.$message({
               type:"success",
               message:res.data.msg
             })
             this.getAllData()
             this.dialogFormVisible = false
           }
        })
        }
      })
    },
    addEmp(){
      this.$refs["empForm"].validate(val=>{
        if(val){
          let params = {
           id:Number(this.empForm.id),
           empName:this.empForm.empName,
           age:Number(this.empForm.age),
           sex:Number(this.empForm.sex),
           deptId:Number(this.empForm.deptId)
          }
         this.axios.post("/api/addEmp",params).then(res=>{
           console.log(res,"请求结果！")
           if(res.data.code == 1000){
             this.$message({
               type:"success",
               message:res.data.msg
             })
             this.getAllData()
             this.dialogFormVisible = false
           }
        })
        }
      })
    },
    sexFilter(val){
     return val.sex == 0?"女":"男"
    },
    handleSizeChange(val){
      this.pageSize = val
      this.getAllData()
    },
    handleCurrentChange(val){
      this.currentPage = val
      this.getData()
    },
    editOpen(row){
      this.empTitle = "编辑员工"
      this.empForm = row
      this.dialogFormVisible = true
    },
    delteEmp(row){
      this.$confirm('确认删除员工信息?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          cancelButtonClass: 'btn-custom-cancel',
          type: 'warning'
        }).then(()=>{
          this.axios.post("/api/deleteEmp",row).then(res=>{
            if(res.data.code == 1000){
              this.$message({
                type:"success",
                message:"删除成功！"
              })
              this.getAllData()
            }
          })
        })
    },
    getAllData(){
      this.currentPage = 1
      this.getData()
    },
    getData() {
      let params = {
        current:this.currentPage,
        size:this.pageSize,
        keyWords:this.filters.keyWords==""?null:this.filters.keyWords,
        empName:this.filters.empName==""?null:this.filters.empName,
        deptId:this.filters.deptId==""?null:this.filters.deptId
      }
      this.axios.post("/api/getEmp",params).then( res=>{
        this.tableData = res.data.list
        this.total = res.data.total
      } )
    }
  },
  mounted() {
    this.getData()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.about{
  width: 80%;
  justify-content: center;
  align-items: center;
  padding: 0 200px;
}
.page-sty{
  float: right;
  margin: 20px 180px 0 0;
}
.add-sty{
  float: right;
  margin: 20px 10px 10px 0px;
}
.dialog-sty{
  padding: 40px;
}
.filters-sty{
  float: left;
  margin: 10px 0 20px 0;
}
</style>
