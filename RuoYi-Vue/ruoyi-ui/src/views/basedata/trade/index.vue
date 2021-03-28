<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="行业名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入行业名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="启用" prop="isUse">
        <el-select v-model="queryParams.isUse" placeholder="请选择启用" clearable size="small">
          <el-option
            v-for="dict in isUseOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
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
          v-hasPermi="['basedata:trade:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['basedata:trade:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['basedata:trade:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:trade:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tradeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="行业名称" align="center" prop="name" />
      <el-table-column label="排序" align="center" prop="showOrder" />
      <el-table-column label="启用" align="center" prop="isUse" :formatter="isUseFormat" />
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
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:trade:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:trade:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改行业对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="行业名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入行业名称" />
        </el-form-item>
        <el-form-item label="排序" prop="showOrder">
          <el-input-number v-model="form.showOrder" controls-position="right" :min="0" />
        </el-form-item>
        <el-form-item label="启用">
          <el-radio-group v-model="form.isUse">
            <el-radio
              v-for="dict in isUseOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
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
import { listTrade, getTrade, delTrade, addTrade, updateTrade, exportTrade } from "@/api/basedata/trade";

export default {
  name: "Trade",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 行业表格数据
      tradeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 启用字典
      isUseOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        showOrder: null,
        isUse: null,
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
    this.getDicts("sys_normal_disable").then(response => {
      this.isUseOptions = response.data;
    });
  },
  methods: {
    /** 查询行业列表 */
    getList() {
      this.loading = true;
      listTrade(this.queryParams).then(response => {
        this.tradeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 启用字典翻译
    isUseFormat(row, column) {
      return this.selectDictLabel(this.isUseOptions, row.isUse);
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
        showOrder: null,
        isUse: "0",
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
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加行业";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTrade(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改行业";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTrade(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTrade(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除行业编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTrade(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有行业数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTrade(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
