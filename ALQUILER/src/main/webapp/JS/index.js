/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import {verificarFormulario} from "./verificarFormulario.js";
const $formulario=document.querySelector(".form-contact");

document.addEventListener("keyup",e=>{
    if(e.target.matches(".form-contact [required]"))
    {
         verificarFormulario(e.target);
    }
   
});
document.addEventListener("DOMContentLoaded",e=>{
   const $inputs=document.querySelectorAll(".form-contact [required]");
   $inputs.forEach(el=>{
      let $span=document.createElement("span");
      $span.id=el.name;
      $span.textContent=el.title;
      $span.classList.add("alert-danger","d-none");
      el.insertAdjacentElement("afterend",$span);
   });
});

