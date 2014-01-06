$(document).ready(function() {
    var GridColumnType = [{
        header: '标示列',
        headerIndex: 'disid',
        width: '200'
    },
    {
        header: '名称',
        headerIndex: 'disname',
        width: '200px'
    },
    {
        header: '上级标示',
        headerIndex: 'disparentId',
        width: '200px'
    }];
    var content = {
        columnModel: GridColumnType,
        dataUrl: appPath + "/commongridtree/initlazy.do",
        lazyLoadUrl: appPath + "/commongridtree/lazy.do",
        idColumn: 'disid',
        //id所在的列,一般是主键(不一定要显示出来)
        parentColumn: 'disparentId',
        //父亲列id
        pageSize: 3,
		height: '300px',
		width: '100%',
        pageBar: true,
        debug: true,
        analyzeAtServer: true,
        //设置了dataUrl属性的时候，如果此属性是false表示分析树形结构在前台进行，默认是后台分析（仅支持java）,体现在json格式不用！
        multiChooseMode: 5,
        //选择模式，共有1，2，3，4，5种。
        tableId: 'testTable',
        //表格树的id
        checkOption: 'multi',
        //1:出现单选按钮,2:出现多选按钮,其他:不出现选择按钮
        rowCount: true,
        //expandColumnNm: 'disparentId',
        //countColumnNm: 'disparentId',
        checkColumnNm: null,//'disparentId',
        //好用...
        onLazyLoadSuccess: function(gt) { //alert('懒加载执行完了..');
        },
        onSuccess: function(gt) { //alert('初次加载表格树执行完了..');
        },
        onPagingSuccess: function(gt) { //alert('翻页执行完了..');
        },
        lazy: true,
        //使用懒加载模式（此时打开全部，关闭全部功能不可使用）
        leafColumn: 'isLeaf'
        //用于判断节点是不是树叶  
    };
    $('#newtableTree').gridTree(content);
});