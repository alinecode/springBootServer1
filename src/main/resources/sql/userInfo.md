sample
===
* 简单查询

	select #use("cols")# from user_info  where  #use("condition")#

pageQuery
===
* 分页查询

	select #page()# from user_info where #use("condition")#

cols
===
	id,uid,deptid,name,nickname,sex,birthday,cardid,signature,school,education,address,phone,email,remark,profilephoto,createdate,createuser,updatedate,updateuser

updateSample
===
	
	id=#id#,uid=#uid#,deptid=#deptid#,name=#name#,nickname=#nickname#,sex=#sex#,birthday=#birthday#,cardid=#cardid#,signature=#signature#,school=#school#,education=#education#,address=#address#,phone=#phone#,email=#email#,remark=#remark#,profilephoto=#profilephoto#,createdate=#createdate#,createuser=#createuser#,updatedate=#updatedate#,updateuser=#updateuser#

condition
===

	1 = 1  
	@if(!isEmpty(id)){
	 and id=#id#
	@}
	@if(!isEmpty(uid)){
	 and uid=#uid#
	@}
	@if(!isEmpty(deptid)){
	 and deptid=#deptid#
	@}
	@if(!isEmpty(name)){
	 and name=#name#
	@}
	@if(!isEmpty(nickname)){
	 and nickname=#nickname#
	@}
	@if(!isEmpty(sex)){
	 and sex=#sex#
	@}
	@if(!isEmpty(birthday)){
	 and birthday=#birthday#
	@}
	@if(!isEmpty(cardid)){
	 and cardid=#cardid#
	@}
	@if(!isEmpty(signature)){
	 and signature=#signature#
	@}
	@if(!isEmpty(school)){
	 and school=#school#
	@}
	@if(!isEmpty(education)){
	 and education=#education#
	@}
	@if(!isEmpty(address)){
	 and address=#address#
	@}
	@if(!isEmpty(phone)){
	 and phone=#phone#
	@}
	@if(!isEmpty(email)){
	 and email=#email#
	@}
	@if(!isEmpty(remark)){
	 and remark=#remark#
	@}
	@if(!isEmpty(profilephoto)){
	 and profilephoto=#profilephoto#
	@}
	@if(!isEmpty(createdate)){
	 and createdate=#createdate#
	@}
	@if(!isEmpty(createuser)){
	 and createuser=#createuser#
	@}
	@if(!isEmpty(updatedate)){
	 and updatedate=#updatedate#
	@}
	@if(!isEmpty(updateuser)){
	 and updateuser=#updateuser#
	@}
	
	