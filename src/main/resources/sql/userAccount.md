pageQuery
===

	select #page()# from user_account where #use("condition")#

sample
===
* 注释###

    select #use("cols")# from user_account  where  #use("condition")#

cols
===
	id,account,password,disablestate,isdel,createdate,updatedate,updateuser

updateSample
===
    
	id=#id#,account=#account#,password=#password#,disablestate=#disablestate#,isdel=#isdel#,createdate=#createdate#,updatedate=#updatedate#,updateuser=#updateuser#

condition
===

    1 = 1  
    @if(!isEmpty(id)){
     and id=#id#
    @}
    @if(!isEmpty(account)){
     and account=#account#
    @}
    @if(!isEmpty(password)){
     and password=#password#
    @}
    @if(!isEmpty(disablestate)){
     and disablestate=#disablestate#
    @}
    @if(!isEmpty(isdel)){
     and isdel=#isdel#
    @}
    @if(!isEmpty(createdate)){
     and createdate=#createdate#
    @}
    @if(!isEmpty(updatedate)){
     and updatedate=#updatedate#
    @}
    @if(!isEmpty(updateuser)){
     and updateuser=#updateuser#
    @}
