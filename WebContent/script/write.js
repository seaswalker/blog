//表单提交检查
function check() {
    var form = document.forms[0];
    //输入的标题
    var title = form.title;
    if(title.value == "") {
        alert("请输入标题");
        title.focus();
        return false;
    }
    //内容
    var content = CKEDITOR.instances.ckeditor.getData();
	if(content == null || content.trim() == "") {
		alert("请输入内容");
		return false;
	}
    //判断标签
    //标签可以为空
    var tags = form.tags;
    var tagsValue = tags.value;
    if(tagsValue != "") {
        var tagArray = tagsValue.split(" ");
        if(tagArray.length > 5) {
            alert("最多5个标签");
            tags.focus();
            return false;
        }
    }
    return true;
}