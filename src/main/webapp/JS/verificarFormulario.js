/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const d=document;

export function verificarFormulario(target)
{
    let patron=target.pattern || target.dataset.pattern;
    if(patron && target.value)
    {
        let reg=new RegExp(patron);
        return !reg.exec(target.value)
        ?d.getElementById(target.name).classList.remove("d-none")
        :d.getElementById(target.name).classList.add("d-none");
    }
    else
    {
        return target==""
        ?d.getElementById(target.name).classList.remove("d-none")
        :d.getElementById(target.name).classList.add("d-none");
    }
}
