<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="目录名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入目录名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属行业" prop="tradeId">
        <el-select v-model="queryParams.tradeId" placeholder="请选择所属行业" clearable size="small">
          <el-option
            v-for="trade in tradeOptions"
            :key="trade.id"
            :label="trade.name"
            :value="trade.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.createdTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
	    <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['basedata:analyseModelCatalog:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="analyseModelCatalogList"
      row-key="id"
      default-expand-all
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="目录名称" align="center" prop="name" />
      <el-table-column label="所属行业" align="center" prop="tradeId" :formatter="nameFormat" />
      <el-table-column label="排序" align="center" prop="showOrder" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['basedata:analyseModelCatalog:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:analyseModelCatalog:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:analyseModelCatalog:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改分析模型目录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="目录名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入目录名称" />
        </el-form-item>
        <el-form-item label="所属行业" prop="tradeId">
          <el-select v-model="form.tradeId" placeholder="请选择所属行业">
            <el-option
              v-for="trade in tradeOptions"
              :key="trade.id"
              :label="trade.name"
              :value="trade.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="父级目录" prop="parentId" >
          <treeselect v-model="form.parentId" :options="analyseModelCatalogOptions" :normalizer="normalizer" placeholder="请选择父级目录" />
        </el-form-item>
        <el-form-item label="排序" prop="showOrder">
          <el-input-number v-model="form.showOrder" placeholder="请输入排序" controls-position="right" :min="0" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAnalyseModelCatalog, getAnalyseModelCatalog, delAnalyseModelCatalog, addAnalyseModelCatalog, updateAnalyseModelCatalog, exportAnalyseModelCatalog, listAnalyseModelCatalogExcludeChild } from "@/api/basedata/analyseModelCatalog";
import { tradeList, selectTradeName} from "@/api/basedata/trade";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "AnalyseModelCatalog",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 分析模型目录表格数据
      analyseModelCatalogList: [],
      // 分析模型目录树选项
      analyseModelCatalogOptions: [],
      //行业下拉
      tradeOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        name: null,
        tradeId: null,
        parentId: null,
        parentIds: null,
        showOrder: null,
        level: null,
        deletedFlag: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null,
        deletedTime: null,
        deletedBy: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getTradeList();
  },
  methods: {
    /* 查询行业下拉列表*/
    getTradeList(){
      tradeList().then(response => {
        this.tradeOptions = response.data;
      });
    },
    /** 查询分析模型目录列表 */
    getList() {
      this.loading = true;
      listAnalyseModelCatalog(this.queryParams).then(response => {
        this.analyseModelCatalogList = this.handleTreeUUID(response.data, "id", "parentId");
        this.loading = false;
      });
    },
    /** 转换分析模型目录数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.id,
        label: node.name,
        children: node.children
      };
    },
	/** 查询部门下拉树结构 */
    getTreeselect() {
      listAnalyseModelCatalog().then(response => {
        this.analyseModelCatalogOptions = [];
        const data = { id: 0, name: '顶级节点', children: [] };
        data.children = this.handleTreeUUID(response.data, "id", "parentId");
        this.analyseModelCatalogOptions.push(data);
      });
    },
    // id翻译
    nameFormat(row, column) {
      return selectTradeName(this.tradeOptions, row.tradeId);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        tradeId: null,
        parentId: null,
        parentIds: [],
        showOrder: null,
        level: null,
        deletedFlag: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null,
        deletedTime: null,
        deletedBy: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.open = true;
      this.title = "添加分析模型目录";
      if (row != undefined) {
        this.form.parentId = row.id;
        this.form.tradeId = row.tradeId;
      }
      this.getTreeselect();
      this.getTradeList();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      if (row != null) {
        this.form.parentId = row.id;
      }
      getAnalyseModelCatalog(row.id).then(response => {
        this.form = response.data;
        this.form.parentIds = this.form.parentIds.split(",");
        this.open = true;
        this.title = "修改分析模型目录";
      });
      listAnalyseModelCatalogExcludeChild(row.id).then(response => {
        this.analyseModelCatalogOptions = [];
        const data = { id: 0, name: '顶级节点', children: [] };
        data.children = this.handleTreeUUID(response.data, "id")
        this.analyseModelCatalogOptions.push(data);
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.parentIds = this.form.parentIds.join(",");
          if (this.form.id != null) {
            updateAnalyseModelCatalog(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAnalyseModelCatalog(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm('是否确认删除分析模型目录编号为"' + row.id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAnalyseModelCatalog(row.id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    }
  }
};
</script>
