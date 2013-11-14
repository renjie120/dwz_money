$(document).ready(function() {
    var GridColumnType = [{
        header: '地域id',
        headerIndex: 'disId',
        width: '100px'
    },
    {
        header: '地域名称',
        headerIndex: 'disName',
        width: '100px',
		columntype : {
				inputtype : 'html',
				htmlStr : '<a href="http://www.baidu.com">$</a>',
				nameId : 'textbox'
			}
    },
    {
        header: '父亲节点名称',
        headerIndex: 'disParent',
        width: '100px'
    },
    {
        header: '测试下拉菜单',
        headerIndex: 'rd',
        width: '300px'
    },
    {
        header: '测试多选',
        headerIndex: 'multi',
        width: '400px'
    },
    {
        header: '测试文本框',
        headerIndex: 'rdvisabled',
        width: '500px'
    },
    {
        header: '测试单选',
        headerIndex: 'rddisbled'
    }];

	var json = [{
            "disId": "2200",
            "disName": "吉林省",
            "disParent": "",
            rddisbled: 1,
            rdvisabled: 0,
            rd: "2",
            radi: "1",
            multi: "1,2"
        },
        {
            "disId": "2201",
            "disName": "长春市",
            "disParent": "2200",
            rddisbled: 0,
            rdvisabled: 0,
            rd: "2",
            radi: "1",
            multi: "1,3"
        },
		{
            "disId": "2201-1",
            "disName": "长春市某个区",
            "disParent": "2201",
            rddisbled: 0,
            rdvisabled: 0,
            rd: "2",
            radi: "1",
            multi: "1,3"
        },
			{
            "disId": "2201-2",
            "disName": "长春市某个区2",
            "disParent": "2201",
            rddisbled: 0,
            rdvisabled: 0,
            rd: "2",
            radi: "1",
            multi: "1,3"
        },
		{
            "disId": "2201-1-1",
            "disName": "长春市某区某街",
            "disParent": "2201-1",
            rddisbled: 0,
            rdvisabled: 0,
            rd: "2",
            radi: "1",
            multi: "1,3"
        },
        {
            "disId": "2202",
            "disName": "吉林市",
            "disParent": "2200",
            rddisbled: 0,
            rdvisabled: 0,
            rd: "2",
            radi: "1",
            multi: "1"
        },
        {
            "disId": "2203",
            "disName": "四平市",
            "disParent": "2200",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "2",
            radi: "1",
            multi: "1"
        },
        {
            "disId": "2204",
            "disName": "辽源市",
            "disParent": "2200",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2205",
            "disName": "通化市",
            "disParent": "2200",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2206",
            "disName": "白山市",
            "disParent": "2200",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2207",
            "disName": "松原市",
            "disParent": "2200",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2208",
            "disName": "白城市",
            "disParent": "2200",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2300",
            "disName": "黑龙江省",
            "disParent": "",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2301",
            "disName": "哈尔滨市",
            "disParent": "2300",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2302",
            "disName": "齐齐哈尔市",
            "disParent": "2300",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2303",
            "disName": "鸡西市",
            "disParent": "2300",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2304",
            "disName": "鹤岗市",
            "disParent": "2300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2305",
            "disName": "双鸭山市",
            "disParent": "2300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2306",
            "disName": "大庆市",
            "disParent": "2300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2307",
            "disName": "伊春市",
            "disParent": "2300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2308",
            "disName": "佳木斯市",
            "disParent": "2300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2309",
            "disName": "七台河市",
            "disParent": "2300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2310",
            "disName": "牡丹江市",
            "disParent": "2300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2311",
            "disName": "黑河市",
            "disParent": "2300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "2312",
            "disName": "绥化市",
            "disParent": "2300",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3100",
            "disName": "上海市",
            "disParent": "",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3200",
            "disName": "江苏省",
            "disParent": "",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3201",
            "disName": "南京市",
            "disParent": "3200",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3202",
            "disName": "无锡市",
            "disParent": "3200",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3203",
            "disName": "徐州市",
            "disParent": "3200",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3204",
            "disName": "常州市",
            "disParent": "3200",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3205",
            "disName": "苏州市",
            "disParent": "3200",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3206",
            "disName": "南通市",
            "disParent": "3200",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3207",
            "disName": "连云港市",
            "disParent": "3200",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3208",
            "disName": "淮安市",
            "disParent": "3200",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3209",
            "disName": "盐城市",
            "disParent": "3200",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3210",
            "disName": "扬州市",
            "disParent": "3200",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3211",
            "disName": "镇江市",
            "disParent": "3200",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3212",
            "disName": "泰州市",
            "disParent": "3200",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3213",
            "disName": "宿迁市",
            "disParent": "3200",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3300",
            "disName": "浙江省",
            "disParent": "",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3301",
            "disName": "杭州市",
            "disParent": "3300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3302",
            "disName": "宁波市",
            "disParent": "3300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3303",
            "disName": "温州市",
            "disParent": "3300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3304",
            "disName": "嘉兴市",
            "disParent": "3300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3305",
            "disName": "湖州市",
            "disParent": "3300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3306",
            "disName": "绍兴市",
            "disParent": "3300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3307",
            "disName": "金华市",
            "disParent": "3300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3308",
            "disName": "衢州市",
            "disParent": "3300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3309",
            "disName": "舟山市",
            "disParent": "3300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3310",
            "disName": "台州市",
            "disParent": "3300",
            rddisbled: 1,
            rdvisabled: 1,
            rd: "1",
            radi: "2",
            multi: "1"
        },
        {
            "disId": "3311",
            "disName": "丽水市",
            "disParent": "3300",
            rddisbled: 0,
            rdvisabled: 1,
            rd: "2",
            radi: "1",
            multi: "1"
        }];
    var content = {
        columnModel: GridColumnType,
        hidddenProperties: ['disId', 'disName'],
        data:json ,
        idColumn: 'disId',
        //id所在的列,一般是主键(不一定要显示出来)
        parentColumn: 'disParent',
        //父亲列id
        rowCountOption: 3,
        height: '300px',
        width: '100%',
        rowCount: true,
        //是否自动计算行数                       
        checkOption: 'multi',
        //1:出现单选按钮,2:出现多选按钮,其他:不出现选择按钮
        allCheck: true,
        //如果是多选,可以选择是否出现全部选择的按钮
        debug: true,
        pageBar: true,
        styleOption: 1,
        pageSize: 3,
        //expandColumnNm:'multi',
        //countColumnNm:'disName',
        //checkColumnNm:'disName',//好用...
        //disabeld:true,//为true就表示表格中的文本域,多选框等为不可编辑状态
        disableOptionColumn: 'rddisbled',
        //用来标识指定的选择框是否禁用的属性,默认没有
        chooesdOptionColumn: 'rddisbled',
        //用来标识默认的就选择多选框的属性,在有多选的选按钮的情况时候有用.
        multiChooseMode: 3,
        expandAll: true,
        //展开全部
        tableId: 'testTable' 
    };
    $('#newtableTree').gridTree(content);
});