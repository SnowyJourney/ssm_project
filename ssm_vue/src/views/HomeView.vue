<template>
  <div>
<!--    新增和其他选项-->
    <div style="margin: 7px 5px">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button>其他</el-button>
    </div>
<!--    搜索框-->
    <div style="margin: 5px 5px">
      <el-input v-model="search" style="width: 30%"  placeholder="请输入给关键字" />
      <el-button style="margin-left: 3px" type="primary" @click="list">搜索</el-button>
    </div>
<!--    内容表格-->
      <el-table :data="tableData" stripe style="width: 90%">
        <el-table-column prop="id" label="ID" sortable></el-table-column>
        <el-table-column prop="name" label="名称" sortable></el-table-column>
        <el-table-column prop="maker" label="厂商"></el-table-column>
        <el-table-column prop="price" label="价格"></el-table-column>
        <el-table-column prop="stock" label="容量"></el-table-column>
        <el-table-column fixed="right" label="操作">
          <template #default="scope">
            <el-button link type="primary"  @click="FurnEdit(scope.row)">编辑</el-button>
<!--            删除弹出确认框-->
            <el-popconfirm title="确认要删除吗?" @confirm="delRow(scope.row.id)">
              <template #reference>
                <el-button>删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
<!--    新增提示框输入内容-->
    <el-dialog v-model="dialogVisible" title="提示" width="30%" :before-close="handleClose">
      <el-form :model="form" :rules="rules" ref="form" label-width="120px">
        <el-form-item label="家具名字" prop="name">
          <el-input v-model="form.name"/>
          {{validErrors.name}}
        </el-form-item>
        <el-form-item label="制造厂商" prop="maker">
          <el-input v-model="form.maker"/>
          {{validErrors.maker}}
        </el-form-item>
        <el-form-item label="定制价格" prop="price">
          <el-input v-model="form.price"/>
          {{validErrors.price}}
        </el-form-item>
        <el-form-item label="存储容量" prop="stock">
          <el-input v-model="form.stock"/>
          {{validErrors.stock}}
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save">确定</el-button>
          <el-button @click="dialogVisible=false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

<!--    添加分页导航-->
    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handlePageSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[3,6,9]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: 'HomeView',
  components: {
  },
  data() {
    return {
      currentPage: 1,
      pageSize: 3,
      total: 10,
      search: '',
      dialogVisible: false, //默认设置不可见
      form: {},//表单数据
      tableData: [],
      rules: {
        name: [{required: true, message: '请输入家居名', trigger: 'blur'}],
        maker: [{required: true, message: '请输入制造商', trigger: 'blur'}],
        price: [{required: true, message: '请输入价格', trigger: 'blur'},
          {pattern: /^(([1-9]\d*)|(0))(\.\d+)?$/, message: '请输入数字', trigger: 'blur'}],
        stock: [{required: true, message: '请输入库存', trigger: 'blur'},
          {pattern: /^(([1-9]\d*)|(0))$/, message: '请输入数字', trigger: 'blur'}]
      },
      // 这个是接受后端数据校验数据失败返回的错误信息
      validErrors:{}
    }
  },
  created() {
    this.list()
  },
  methods:{
    add(){
      //显示提交表单
      this.dialogVisible = true;
      //清空上次填写表单数据
      this.form ={};
      //清空上次表单前端校验提示数据
      if(this.$refs['form'] !== undefined)
      this.$refs['form'].resetFields();
      // 清空后端校验提示信息
      this.validErrors={};
    },
    save(){
      // 有数据就是修改请求
      console.log(this.form.id)
      if(this.form.id){
        request.put("/Fur/modifyOne",this.form).then(res =>{
          if(res.code ===200){
            this.$message({message: '更新成功', type: 'success'});
            // 更新成功就刷新页面
            this.dialogVisible = false;
            this.list();
          }else{
            this.$message({message: '更新失败',type: 'error'});
            this.dialogVisible = false;
          }
        })
      }else {
        //提示框为空就发送save请求
        this.$refs['form'].validate(valid=>{
          // 如果验证通过就提交数据
          if(valid){
            request.post("/Fur/save", this.form)
                .then(res => {
                  // 如果后端校验通过成功存入
                  if(res.code==200){
                    this.dialogVisible = false;
                    this.list();
                  }else{
                    //后端校验数据没有通过就封装错误信息到数据池然后前端显示
                    this.validErrors.name=res.data.error.name;
                    this.validErrors.maker=res.data.error.maker;
                    this.validErrors.price=res.data.error.price;
                    this.validErrors.stock=res.data.error.stock;
                  }
                })
          }
          else {
            //前端没有验证通过就不提交
            this.$message({type:"error",message:"请填写完整信息"});
            return false;
          }
        })
      }
    },
    list(){
      // 未分页前请求
      // request.get('/Fur/listFurn').then(res =>{
      //   // console.log(res)
      // this.tableData=res.data.furnList;
      // })
      request.get('/Fur/search',{
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          name: this.search
        }}).then(res=>{
          this.tableData=res.data.furnList.list;
          this.total=res.data.furnList.total;
      })
    },
    FurnEdit(row){
      let stringRow = JSON.stringify(row);
      this.form = JSON.parse(stringRow);
      this.dialogVisible = true;
    },
    delRow(id){
      request.delete("/Fur/del/"+id).then(res=>{
        if(res.code === 200){
          this.$message({type:"success",message: "删除成功"});
        }else{
          this.$message({type:"error",message: "删除失败"});
        }
        this.dialogVisible=false;
        this.list();
      })
    },
    handleCurrentChange(pageNum){
      this.currentPage=pageNum;
      this.list();
    },
    handlePageSizeChange(pageSize){
      this.pageSize=pageSize;
      this.list();
    }
  }
}
</script>
