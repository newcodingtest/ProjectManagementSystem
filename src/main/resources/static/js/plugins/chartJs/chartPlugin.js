/*!
 * @license
 * chartjs-plugin-datalabels
 * http://chartjs.org/
 * Version: 0.5.0
 *
 * Copyright 2018 Chart.js Contributors
 * Released under the MIT license
 * https://github.com/chartjs/chartjs-plugin-datalabels/blob/master/LICENSE.md
 */
!function(t,e){"object"==typeof exports&&"undefined"!=typeof module?module.exports=e(require("chart.js")):"function"==typeof define&&define.amd?define(["chart.js"],e):t.ChartDataLabels=e(t.Chart)}(this,function(t){"use strict";var e=(t=t&&t.hasOwnProperty("default")?t.default:t).helpers,r=function(){if("undefined"!=typeof window){if(window.devicePixelRatio)return window.devicePixelRatio;var t=window.screen;if(t)return(t.deviceXDPI||1)/(t.logicalXDPI||1)}return 1}(),n={toTextLines:function(t){var r,n=[];for(t=[].concat(t);t.length;)"string"==typeof(r=t.pop())?n.unshift.apply(n,r.split("\n")):Array.isArray(r)?t.push.apply(t,r):e.isNullOrUndef(t)||n.unshift(""+r);return n},toFontString:function(t){return!t||e.isNullOrUndef(t.size)||e.isNullOrUndef(t.family)?null:(t.style?t.style+" ":"")+(t.weight?t.weight+" ":"")+t.size+"px "+t.family},textSize:function(t,e,r){var n,i=[].concat(e),a=i.length,o=t.font,l=0;for(t.font=r.string,n=0;n<a;++n)l=Math.max(t.measureText(i[n]).width,l);return t.font=o,{height:a*r.lineHeight,width:l}},parseFont:function(r){var i=t.defaults.global,a=e.valueOrDefault(r.size,i.defaultFontSize),o={family:e.valueOrDefault(r.family,i.defaultFontFamily),lineHeight:e.options.toLineHeight(r.lineHeight,a),size:a,style:e.valueOrDefault(r.style,i.defaultFontStyle),weight:e.valueOrDefault(r.weight,null),string:""};return o.string=n.toFontString(o),o},bound:function(t,e,r){return Math.max(t,Math.min(e,r))},arrayDiff:function(t,e){var r,n,i,a,o=t.slice(),l=[];for(r=0,i=e.length;r<i;++r)a=e[r],-1===(n=o.indexOf(a))?l.push([a,1]):o.splice(n,1);for(r=0,i=o.length;r<i;++r)l.push([o[r],-1]);return l},rasterize:function(t){return Math.round(t*r)/r}};function i(t,e){var r=e.x,n=e.y;if(null===r)return{x:0,y:-1};if(null===n)return{x:1,y:0};var i=t.x-r,a=t.y-n,o=Math.sqrt(i*i+a*a);return{x:o?i/o:0,y:o?a/o:-1}}var a=0,o=1,l=2,s=4,u=8;function d(t,e,r){var n=a;return t<r.left?n|=o:t>r.right&&(n|=l),e<r.top?n|=u:e>r.bottom&&(n|=s),n}function f(t,e){var r,n,i=e.anchor,a=t;return e.clamp&&(a=function(t,e){for(var r,n,i,a=t.x0,f=t.y0,c=t.x1,h=t.y1,x=d(a,f,e),y=d(c,h,e);x|y&&!(x&y);)(r=x||y)&u?(n=a+(c-a)*(e.top-f)/(h-f),i=e.top):r&s?(n=a+(c-a)*(e.bottom-f)/(h-f),i=e.bottom):r&l?(i=f+(h-f)*(e.right-a)/(c-a),n=e.right):r&o&&(i=f+(h-f)*(e.left-a)/(c-a),n=e.left),r===x?x=d(a=n,f=i,e):y=d(c=n,h=i,e);return{x0:a,x1:c,y0:f,y1:h}}(a,e.area)),"start"===i?(r=a.x0,n=a.y0):"end"===i?(r=a.x1,n=a.y1):(r=(a.x0+a.x1)/2,n=(a.y0+a.y1)/2),function(t,e,r,n,i){switch(i){case"center":r=n=0;break;case"bottom":r=0,n=1;break;case"right":r=1,n=0;break;case"left":r=-1,n=0;break;case"top":r=0,n=-1;break;case"start":r=-r,n=-n;break;case"end":break;default:i*=Math.PI/180,r=Math.cos(i),n=Math.sin(i)}return{x:t,y:e,vx:r,vy:n}}(r,n,t.vx,t.vy,e.align)}var c={arc:function(t,e){var r=(t.startAngle+t.endAngle)/2,n=Math.cos(r),i=Math.sin(r),a=t.innerRadius,o=t.outerRadius;return f({x0:t.x+n*a,y0:t.y+i*a,x1:t.x+n*o,y1:t.y+i*o,vx:n,vy:i},e)},point:function(t,e){var r=i(t,e.origin),n=r.x*t.radius,a=r.y*t.radius;return f({x0:t.x-n,y0:t.y-a,x1:t.x+n,y1:t.y+a,vx:r.x,vy:r.y},e)},rect:function(t,e){var r=i(t,e.origin),n=t.x,a=t.y,o=0,l=0;return t.horizontal?(n=Math.min(t.x,t.base),o=Math.abs(t.base-t.x)):(a=Math.min(t.y,t.base),l=Math.abs(t.base-t.y)),f({x0:n,y0:a+l,x1:n+o,y1:a,vx:r.x,vy:r.y},e)},fallback:function(t,e){var r=i(t,e.origin);return f({x0:t.x,y0:t.y,x1:t.x,y1:t.y,vx:r.x,vy:r.y},e)}},h=t.helpers,x=n.rasterize;function y(t){var e=t._model.horizontal,r=t._scale||e&&t._xScale||t._yScale;if(!r)return null;if(void 0!==r.xCenter&&void 0!==r.yCenter)return{x:r.xCenter,y:r.yCenter};var n=r.getBasePixel();return e?{x:n,y:null}:{x:null,y:n}}function v(t,e,r){var n=t.shadowBlur,i=r.stroked,a=x(r.x),o=x(r.y),l=x(r.w);i&&t.strokeText(e,a,o,l),r.filled&&(n&&i&&(t.shadowBlur=0),t.fillText(e,a,o,l),n&&i&&(t.shadowBlur=n))}var b=function(t,e,r,n){var i=this;i._config=t,i._index=n,i._model=null,i._rects=null,i._ctx=e,i._el=r};h.extend(b.prototype,{_modelize:function(e,r,i,a){var o,l=this._index,s=h.options.resolve,u=n.parseFont(s([i.font,{}],a,l)),d=s([i.color,t.defaults.global.defaultFontColor],a,l);return{align:s([i.align,"center"],a,l),anchor:s([i.anchor,"center"],a,l),area:a.chart.chartArea,backgroundColor:s([i.backgroundColor,null],a,l),borderColor:s([i.borderColor,null],a,l),borderRadius:s([i.borderRadius,0],a,l),borderWidth:s([i.borderWidth,0],a,l),clamp:s([i.clamp,!1],a,l),clip:s([i.clip,!1],a,l),color:d,display:e,font:u,lines:r,offset:s([i.offset,0],a,l),opacity:s([i.opacity,1],a,l),origin:y(this._el),padding:h.options.toPadding(s([i.padding,0],a,l)),positioner:(o=this._el,o instanceof t.elements.Arc?c.arc:o instanceof t.elements.Point?c.point:o instanceof t.elements.Rectangle?c.rect:c.fallback),rotation:s([i.rotation,0],a,l)*(Math.PI/180),size:n.textSize(this._ctx,r,u),textAlign:s([i.textAlign,"start"],a,l),textShadowBlur:s([i.textShadowBlur,0],a,l),textShadowColor:s([i.textShadowColor,d],a,l),textStrokeColor:s([i.textStrokeColor,d],a,l),textStrokeWidth:s([i.textStrokeWidth,0],a,l)}},update:function(t){var e,r,i,a=this,o=null,l=null,s=a._index,u=a._config,d=h.options.resolve([u.display,!0],t,s);d&&(e=t.dataset.data[s],r=h.valueOrDefault(h.callback(u.formatter,[e,t]),e),(i=h.isNullOrUndef(r)?[]:n.toTextLines(r)).length&&(l=function(t){var e=t.borderWidth||0,r=t.padding,n=t.size.height,i=t.size.width,a=-i/2,o=-n/2;return{frame:{x:a-r.left-e,y:o-r.top-e,w:i+r.width+2*e,h:n+r.height+2*e},text:{x:a,y:o,w:i,h:n}}}(o=a._modelize(d,i,u,t)))),a._model=o,a._rects=l},geometry:function(){return this._rects?this._rects.frame:{}},rotation:function(){return this._model?this._model.rotation:0},visible:function(){return this._model&&this._model.opacity},model:function(){return this._model},draw:function(t,e){var r,i=t.ctx,a=this._model,o=this._rects;this.visible()&&(i.save(),a.clip&&(r=a.area,i.beginPath(),i.rect(r.left,r.top,r.right-r.left,r.bottom-r.top),i.clip()),i.globalAlpha=n.bound(0,a.opacity,1),i.translate(x(e.x),x(e.y)),i.rotate(a.rotation),function(t,e,r){var n=r.backgroundColor,i=r.borderColor,a=r.borderWidth;(n||i&&a)&&(t.beginPath(),h.canvas.roundedRect(t,x(e.x)+a/2,x(e.y)+a/2,x(e.w)-a,x(e.h)-a,r.borderRadius),t.closePath(),n&&(t.fillStyle=n,t.fill()),i&&a&&(t.strokeStyle=i,t.lineWidth=a,t.lineJoin="miter",t.stroke()))}(i,o.frame,a),function(t,e,r,n){var i,a=n.textAlign,o=n.color,l=!!o,s=n.font,u=e.length,d=n.textStrokeColor,f=n.textStrokeWidth,c=d&&f;if(u&&(l||c))for(r=function(t,e,r){var n=r.lineHeight,i=t.w,a=t.x;return"center"===e?a+=i/2:"end"!==e&&"right"!==e||(a+=i),{h:n,w:i,x:a,y:t.y+n/2}}(r,a,s),t.font=s.string,t.textAlign=a,t.textBaseline="middle",t.shadowBlur=n.textShadowBlur,t.shadowColor=n.textShadowColor,l&&(t.fillStyle=o),c&&(t.lineJoin="round",t.lineWidth=f,t.strokeStyle=d),i=0,u=e.length;i<u;++i)v(t,e[i],{stroked:c,filled:l,w:r.w,x:r.x,y:r.y+r.h*i})}(i,a.lines,o.text,a),i.restore())}});var _=t.helpers,p=Number.MIN_SAFE_INTEGER||-9007199254740991,g=Number.MAX_SAFE_INTEGER||9007199254740991;function m(t,e,r){var n=Math.cos(r),i=Math.sin(r),a=e.x,o=e.y;return{x:a+n*(t.x-a)-i*(t.y-o),y:o+i*(t.x-a)+n*(t.y-o)}}function w(t,e){var r,n,i,a,o,l=g,s=p,u=e.origin;for(r=0;r<t.length;++r)i=(n=t[r]).x-u.x,a=n.y-u.y,o=e.vx*i+e.vy*a,l=Math.min(l,o),s=Math.max(s,o);return{min:l,max:s}}function k(t,e){var r=e.x-t.x,n=e.y-t.y,i=Math.sqrt(r*r+n*n);return{vx:(e.x-t.x)/i,vy:(e.y-t.y)/i,origin:t,ln:i}}var M=function(){this._rotation=0,this._rect={x:0,y:0,w:0,h:0}};function S(t,e,r){var n=e.positioner(t,e),i=n.vx,a=n.vy;if(!i&&!a)return{x:n.x,y:n.y};var o=r.w,l=r.h,s=e.rotation,u=Math.abs(o/2*Math.cos(s))+Math.abs(l/2*Math.sin(s)),d=Math.abs(o/2*Math.sin(s))+Math.abs(l/2*Math.cos(s)),f=1/Math.max(Math.abs(i),Math.abs(a));return u*=i*f,d*=a*f,u+=e.offset*i,d+=e.offset*a,{x:n.x+u,y:n.y+d}}_.extend(M.prototype,{center:function(){var t=this._rect;return{x:t.x+t.w/2,y:t.y+t.h/2}},update:function(t,e,r){this._rotation=r,this._rect={x:e.x+t.x,y:e.y+t.y,w:e.w,h:e.h}},contains:function(t){var e=this._rect;return!((t=m(t,this.center(),-this._rotation)).x<e.x-1||t.y<e.y-1||t.x>e.x+e.w+2||t.y>e.y+e.h+2)},intersects:function(t){var e,r,n,i=this._points(),a=t._points(),o=[k(i[0],i[1]),k(i[0],i[3])];for(this._rotation!==t._rotation&&o.push(k(a[0],a[1]),k(a[0],a[3])),e=0;e<o.length;++e)if(r=w(i,o[e]),n=w(a,o[e]),r.max<n.min||n.max<r.min)return!1;return!0},_points:function(){var t=this._rect,e=this._rotation,r=this.center();return[m({x:t.x,y:t.y},r,e),m({x:t.x+t.w,y:t.y},r,e),m({x:t.x+t.w,y:t.y+t.h},r,e),m({x:t.x,y:t.y+t.h},r,e)]}});var C={prepare:function(t){var e,r,n,i,a,o=[];for(e=0,n=t.length;e<n;++e)for(r=0,i=t[e].length;r<i;++r)a=t[e][r],o.push(a),a.$layout={_box:new M,_hidable:!1,_visible:!0,_set:e,_idx:r};return o.sort(function(t,e){var r=t.$layout,n=e.$layout;return r._idx===n._idx?r._set-n._set:n._idx-r._idx}),this.update(o),o},update:function(t){var e,r,n,i,a,o=!1;for(e=0,r=t.length;e<r;++e)i=(n=t[e]).model(),(a=n.$layout)._hidable=i&&"auto"===i.display,a._visible=n.visible(),o|=a._hidable;o&&function(t){var e,r,n,i,a,o;for(e=0,r=t.length;e<r;++e)(i=(n=t[e]).$layout)._visible&&(a=n.geometry(),o=S(n._el._model,n.model(),a),i._box.update(o,a,n.rotation()));(function(t,e){var r,n,i,a;for(r=t.length-1;r>=0;--r)for(i=t[r].$layout,n=r-1;n>=0&&i._visible;--n)(a=t[n].$layout)._visible&&i._box.intersects(a._box)&&e(i,a)})(t,function(t,e){var r=t._hidable,n=e._hidable;r&&n||n?e._visible=!1:r&&(t._visible=!1)})}(t)},lookup:function(t,e){var r,n;for(r=t.length-1;r>=0;--r)if((n=t[r].$layout)&&n._visible&&n._box.contains(e))return{dataset:n._set,label:t[r]};return null},draw:function(t,e){var r,n,i,a,o,l;for(r=0,n=e.length;r<n;++r)(a=(i=e[r]).$layout)._visible&&(o=i.geometry(),l=S(i._el._view,i.model(),o),a._box.update(l,o,i.rotation()),i.draw(t,l))}},z=t.helpers,A={align:"center",anchor:"center",backgroundColor:null,borderColor:null,borderRadius:0,borderWidth:0,clamp:!1,clip:!1,color:void 0,display:!0,font:{family:void 0,lineHeight:1.2,size:void 0,style:void 0,weight:null},formatter:function(t){if(z.isNullOrUndef(t))return null;var e,r,n,i=t;if(z.isObject(t))if(z.isNullOrUndef(t.label))if(z.isNullOrUndef(t.r))for(i="",n=0,r=(e=Object.keys(t)).length;n<r;++n)i+=(0!==n?", ":"")+e[n]+": "+t[e[n]];else i=t.r;else i=t.label;return""+i},listeners:{},offset:4,opacity:1,padding:{top:4,right:4,bottom:4,left:4},rotation:0,textAlign:"start",textStrokeColor:void 0,textStrokeWidth:0,textShadowBlur:0,textShadowColor:void 0},O=t.helpers,D="$datalabels";function $(t,e,r){var n=e&&e[r.dataset];if(n){var i=r.label,a=i.$context;!0===O.callback(n,[a])&&(t[D]._dirty=!0,i.update(a))}}function P(t,e){var r,n,i=t[D],a=i._listeners;if(a.enter||a.leave){if("mousemove"===e.type)n=C.lookup(i._labels,e);else if("mouseout"!==e.type)return;r=i._hovered,i._hovered=n,function(t,e,r,n){var i,a;(r||n)&&(r?n?r.label!==n.label&&(a=i=!0):a=!0:i=!0,a&&$(t,e.leave,r),i&&$(t,e.enter,n))}(t,a,r,n)}}t.defaults.global.plugins.datalabels=A,t.defaults.global.plugins.datalabels=A;var N={id:"datalabels",beforeInit:function(t){t[D]={_actives:[]}},beforeUpdate:function(t){var e=t[D];e._listened=!1,e._listeners={},e._datasets=[],e._labels=[]},afterDatasetUpdate:function(t,e,r){var n,i,a,o=e.index,l=t[D],s=l._datasets[o]=[],u=t.isDatasetVisible(o),d=t.data.datasets[o],f=function(t,e){var r=t.datalabels;return!1===r?null:(!0===r&&(r={}),O.merge({},[e,r]))}(d,r),c=e.meta.data||[],h=c.length,x=t.ctx;for(x.save(),n=0;n<h;++n)i=c[n],u&&i&&!i.hidden&&!i._model.skip?(s.push(a=new b(f,x,i,n)),a.update(a.$context={active:!1,chart:t,dataIndex:n,dataset:d,datasetIndex:o})):a=null,i[D]=a;x.restore(),O.merge(l._listeners,f.listeners||{},{merger:function(t,r,n){r[t]=r[t]||{},r[t][e.index]=n[t],l._listened=!0}})},afterUpdate:function(t,e){t[D]._labels=C.prepare(t[D]._datasets,e)},afterDatasetsDraw:function(t){C.draw(t,t[D]._labels)},beforeEvent:function(t,e){if(t[D]._listened)switch(e.type){case"mousemove":case"mouseout":P(t,e);break;case"click":!function(t,e){var r=t[D],n=r._listeners.click,i=n&&C.lookup(r._labels,e);i&&$(t,n,i)}(t,e)}},afterEvent:function(t){var e,r,i,a,o=t[D],l=o._actives,s=o._actives=t.lastActive||[],u=n.arrayDiff(l,s);for(e=0,r=u.length;e<r;++e)(i=u[e])[1]&&(a=i[0][D])&&(a.$context.active=1===i[1],a.update(a.$context));(o._dirty||u.length)&&(C.update(o._labels),t.animating||t.render()),delete o._dirty}};return t.plugins.register(N),N});


// pie % 노출 라이브러리
!function(e){"function"==typeof define&&define.amd?define([],e):"object"==typeof exports?module.exports=e():window.wNumb=e()}(function(){"use strict";function e(e){return e.split("").reverse().join("")}function t(e,t){return e.substring(0,t.length)===t}function n(e,t){return e.slice(-1*t.length)===t}function r(e,t,n){if((e[t]||e[n])&&e[t]===e[n])throw new Error(t)}function i(e){return"number"==typeof e&&isFinite(e)}function o(e,t){return e=e.toString().split("e"),e=Math.round(+(e[0]+"e"+(e[1]?+e[1]+t:t))),e=e.toString().split("e"),(+(e[0]+"e"+(e[1]?+e[1]-t:-t))).toFixed(t)}function f(t,n,r,f,u,s,c,a,d,p,l,h){var g,v,m,w=h,x="",y="";return s&&(h=s(h)),!!i(h)&&(t!==!1&&0===parseFloat(h.toFixed(t))&&(h=0),h<0&&(g=!0,h=Math.abs(h)),t!==!1&&(h=o(h,t)),h=h.toString(),h.indexOf(".")!==-1?(v=h.split("."),m=v[0],r&&(x=r+v[1])):m=h,n&&(m=e(m).match(/.{1,3}/g),m=e(m.join(e(n)))),g&&a&&(y+=a),f&&(y+=f),g&&d&&(y+=d),y+=m,y+=x,u&&(y+=u),p&&(y=p(y,w)),y)}function u(e,r,o,f,u,s,c,a,d,p,l,h){var g,v="";return l&&(h=l(h)),!(!h||"string"!=typeof h)&&(a&&t(h,a)&&(h=h.replace(a,""),g=!0),f&&t(h,f)&&(h=h.replace(f,"")),d&&t(h,d)&&(h=h.replace(d,""),g=!0),u&&n(h,u)&&(h=h.slice(0,-1*u.length)),r&&(h=h.split(r).join("")),o&&(h=h.replace(o,".")),g&&(v+="-"),v+=h,v=v.replace(/[^0-9\.\-.]/g,""),""!==v&&(v=Number(v),c&&(v=c(v)),!!i(v)&&v))}function s(e){var t,n,i,o={};for(void 0===e.suffix&&(e.suffix=e.postfix),t=0;t<d.length;t+=1)if(n=d[t],i=e[n],void 0===i)"negative"!==n||o.negativeBefore?"mark"===n&&"."!==o.thousand?o[n]=".":o[n]=!1:o[n]="-";else if("decimals"===n){if(!(i>=0&&i<8))throw new Error(n);o[n]=i}else if("encoder"===n||"decoder"===n||"edit"===n||"undo"===n){if("function"!=typeof i)throw new Error(n);o[n]=i}else{if("string"!=typeof i)throw new Error(n);o[n]=i}return r(o,"mark","thousand"),r(o,"prefix","negative"),r(o,"prefix","negativeBefore"),o}function c(e,t,n){var r,i=[];for(r=0;r<d.length;r+=1)i.push(e[d[r]]);return i.push(n),t.apply("",i)}function a(e){return this instanceof a?void("object"==typeof e&&(e=s(e),this.to=function(t){return c(e,f,t)},this.from=function(t){return c(e,u,t)})):new a(e)}var d=["decimals","thousand","mark","prefix","suffix","encoder","decoder","negativeBefore","negative","edit","undo"];return a});
// pie % e


// boxplot lib ie11
(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports, require('chart.js')) :
  typeof define === 'function' && define.amd ? define(['exports', 'chart.js'], factory) :
  (global = global || self, factory(global.ChartBoxPlot = {}, global.Chart));
}(this, function (exports, Chart) { 'use strict';

  function _defineProperty(obj, key, value) {
    if (key in obj) {
      Object.defineProperty(obj, key, {
        value: value,
        enumerable: true,
        configurable: true,
        writable: true
      });
    } else {
      obj[key] = value;
    }

    return obj;
  }

  function _objectSpread(target) {
    for (var i = 1; i < arguments.length; i++) {
      var source = arguments[i] != null ? arguments[i] : {};
      var ownKeys = Object.keys(source);

      if (typeof Object.getOwnPropertySymbols === 'function') {
        ownKeys = ownKeys.concat(Object.getOwnPropertySymbols(source).filter(function (sym) {
          return Object.getOwnPropertyDescriptor(source, sym).enumerable;
        }));
      }

      ownKeys.forEach(function (key) {
        _defineProperty(target, key, source[key]);
      });
    }

    return target;
  }

  function _slicedToArray(arr, i) {
    return _arrayWithHoles(arr) || _iterableToArrayLimit(arr, i) || _nonIterableRest();
  }

  function _arrayWithHoles(arr) {
    if (Array.isArray(arr)) return arr;
  }

  function _iterableToArrayLimit(arr, i) {
    var _arr = [];
    var _n = true;
    var _d = false;
    var _e = undefined;

    try {
      for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) {
        _arr.push(_s.value);

        if (i && _arr.length === i) break;
      }
    } catch (err) {
      _d = true;
      _e = err;
    } finally {
      try {
        if (!_n && _i["return"] != null) _i["return"]();
      } finally {
        if (_d) throw _e;
      }
    }

    return _arr;
  }

  function _nonIterableRest() {
    throw new TypeError("Invalid attempt to destructure non-iterable instance");
  }

  function ascending(a, b) {
    return a - b;
  }

  function quantiles(d, quantiles) {
    d = d.slice().sort(ascending);
    var n_1 = d.length - 1;
    return quantiles.map(function (q) {
      if (q === 0) return d[0];else if (q === 1) return d[n_1];
      var index = 1 + q * n_1,
          lo = Math.floor(index),
          h = index - lo,
          a = d[lo - 1];
      return h === 0 ? a : a + h * (d[lo] - a);
    });
  }

  // See <http://en.wikipedia.org/wiki/Kernel_(statistics)>.
  function gaussian(u) {
    return 1 / Math.sqrt(2 * Math.PI) * Math.exp(-.5 * u * u);
  }

  // Welford's algorithm.
  function mean(x) {
    var n = x.length;
    if (n === 0) return NaN;
    var m = 0,
        i = -1;

    while (++i < n) m += (x[i] - m) / (i + 1);

    return m;
  }

  // Also known as the sample variance, where the denominator is n - 1.

  function variance(x) {
    var n = x.length;
    if (n < 1) return NaN;
    if (n === 1) return 0;
    var mean$$1 = mean(x),
        i = -1,
        s = 0;

    while (++i < n) {
      var v = x[i] - mean$$1;
      s += v * v;
    }

    return s / (n - 1);
  }

  function iqr(x) {
    var quartiles = quantiles(x, [.25, .75]);
    return quartiles[1] - quartiles[0];
  }

  // Visualization. Wiley.

  function nrd(x) {
    var h = iqr(x) / 1.34;
    return 1.06 * Math.min(Math.sqrt(variance(x)), h) * Math.pow(x.length, -1 / 5);
  }

  function functor(v) {
    return typeof v === "function" ? v : function () {
      return v;
    };
  }

  function kde() {
    var kernel = gaussian,
        sample = [],
        bandwidth = nrd;

    function kde(points, i) {
      var bw = bandwidth.call(this, sample);
      return points.map(function (x) {
        var i = -1,
            y = 0,
            n = sample.length;

        while (++i < n) {
          y += kernel((x - sample[i]) / bw);
        }

        return [x, y / bw / n];
      });
    }

    kde.kernel = function (x) {
      if (!arguments.length) return kernel;
      kernel = x;
      return kde;
    };

    kde.sample = function (x) {
      if (!arguments.length) return sample;
      sample = x;
      return kde;
    };

    kde.bandwidth = function (x) {
      if (!arguments.length) return bandwidth;
      bandwidth = functor(x);
      return kde;
    };

    return kde;
  }

  function extent(arr) {
    return arr.reduce(function (acc, v) {
      return [Math.min(acc[0], v), Math.max(acc[1], v)];
    }, [Number.POSITIVE_INFINITY, Number.NEGATIVE_INFINITY]);
  }

  function whiskers(boxplot, arr) {
    var iqr = boxplot.q3 - boxplot.q1; // since top left is max

    var whiskerMin = Math.max(boxplot.min, boxplot.q1 - iqr);
    var whiskerMax = Math.min(boxplot.max, boxplot.q3 + iqr);

    if (Array.isArray(arr)) {
      // compute the closest real element
      for (var i = 0; i < arr.length; i++) {
        var v = arr[i];

        if (v >= whiskerMin) {
          whiskerMin = v;
          break;
        }
      }

      for (var _i = arr.length - 1; _i >= 0; _i--) {
        var _v = arr[_i];

        if (_v <= whiskerMax) {
          whiskerMax = _v;
          break;
        }
      }
    }

    return {
      whiskerMin: whiskerMin,
      whiskerMax: whiskerMax
    };
  }
  function boxplotStats(arr) {
    // console.assert(Array.isArray(arr));
    if (arr.length === 0) {
      return {
        min: NaN,
        max: NaN,
        median: NaN,
        q1: NaN,
        q3: NaN,
        whiskerMin: NaN,
        whiskerMax: NaN,
        outliers: []
      };
    }

    arr = arr.filter(function (v) {
      return typeof v === 'number' && !isNaN(v);
    });
    arr.sort(function (a, b) {
      return a - b;
    });

    var _quantiles = quantiles(arr, [0.5, 0.25, 0.75]),
        _quantiles2 = _slicedToArray(_quantiles, 3),
        median = _quantiles2[0],
        q1 = _quantiles2[1],
        q3 = _quantiles2[2];

    var minmax = extent(arr);
    var base = {
      min: minmax[0],
      max: minmax[1],
      median: median,
      q1: q1,
      q3: q3,
      outliers: []
    };

    var _whiskers = whiskers(base, arr),
        whiskerMin = _whiskers.whiskerMin,
        whiskerMax = _whiskers.whiskerMax;

    base.outliers = arr.filter(function (v) {
      return v < whiskerMin || v > whiskerMax;
    });
    base.whiskerMin = whiskerMin;
    base.whiskerMax = whiskerMax;
    return base;
  }
  function violinStats(arr) {
    // console.assert(Array.isArray(arr));
    if (arr.length === 0) {
      return {
        outliers: []
      };
    }

    arr = arr.filter(function (v) {
      return typeof v === 'number' && !isNaN(v);
    });
    arr.sort(function (a, b) {
      return a - b;
    });
    var minmax = extent(arr);
    return {
      min: minmax[0],
      max: minmax[1],
      median: quantiles(arr, [0.5])[0],
      kde: kde().sample(arr)
    };
  }
  function asBoxPlotStats(value) {
    if (!value) {
      return null;
    }

    if (typeof value.median === 'number' && typeof value.q1 === 'number' && typeof value.q3 === 'number') {
      // sounds good, check for helper
      if (typeof value.whiskerMin === 'undefined') {
        var _whiskers2 = whiskers(value, Array.isArray(value.items) ? value.items.slice().sort(function (a, b) {
          return a - b;
        }) : null),
            whiskerMin = _whiskers2.whiskerMin,
            whiskerMax = _whiskers2.whiskerMax;

        value.whiskerMin = whiskerMin;
        value.whiskerMax = whiskerMax;
      }

      return value;
    }

    if (!Array.isArray(value)) {
      return undefined;
    }

    if (value.__stats === undefined) {
      value.__stats = boxplotStats(value);
    }

    return value.__stats;
  }
  function asViolinStats(value) {
    if (!value) {
      return null;
    }

    if (typeof value.median === 'number' && (typeof value.kde === 'function' || Array.isArray(value.coords))) {
      return value;
    }

    if (!Array.isArray(value)) {
      return undefined;
    }

    if (value.__kde === undefined) {
      value.__kde = violinStats(value);
    }

    return value.__kde;
  }
  function asValueStats(value, minStats, maxStats) {
    if (typeof value[minStats] === 'number' && typeof value[maxStats] === 'number') {
      return value;
    }

    if (!Array.isArray(value) || value.length === 0) {
      return undefined;
    }

    return asBoxPlotStats(value);
  }
  function getRightValue(rawValue) {
    if (!rawValue) {
      return rawValue;
    }

    if (typeof rawValue === 'number' || typeof rawValue === 'string') {
      return Number(rawValue);
    }

    var b = asBoxPlotStats(rawValue);
    return b ? b.median : rawValue;
  }
  var commonScaleOptions = {
    ticks: {
      minStats: 'min',
      maxStats: 'max'
    }
  };
  function commonDataLimits(extraCallback) {
    var _this = this;

    var chart = this.chart;
    var isHorizontal = this.isHorizontal();
    var tickOpts = this.options.ticks;
    var minStats = tickOpts.minStats;
    var maxStats = tickOpts.maxStats;

    var matchID = function matchID(meta) {
      return isHorizontal ? meta.xAxisID === _this.id : meta.yAxisID === _this.id;
    }; // First Calculate the range


    this.min = null;
    this.max = null; // Regular charts use x, y values
    // For the boxplot chart we have rawValue.min and rawValue.max for each point

    chart.data.datasets.forEach(function (d, i) {
      var meta = chart.getDatasetMeta(i);

      if (!chart.isDatasetVisible(i) || !matchID(meta)) {
        return;
      }

      d.data.forEach(function (value, j) {
        if (!value || meta.data[j].hidden) {
          return;
        }

        var stats = asValueStats(value, minStats, maxStats);

        if (!stats) {
          return;
        }

        if (_this.min === null || stats[minStats] < _this.min) {
          _this.min = stats[minStats];
        }

        if (_this.max === null || stats[maxStats] > _this.max) {
          _this.max = stats[maxStats];
        }

        if (extraCallback) {
          extraCallback(stats);
        }
      });
    });
  }
  function rnd(seed) {
    // Adapted from http://indiegamr.com/generate-repeatable-random-numbers-in-js/
    if (seed === undefined) {
      seed = Date.now();
    }

    return function () {
      seed = (seed * 9301 + 49297) % 233280;
      return seed / 233280;
    };
  }

  var defaults = _objectSpread({}, Chart.defaults.global.elements.rectangle, {
    borderWidth: 1,
    outlierRadius: 2,
    outlierColor: Chart.defaults.global.elements.rectangle.backgroundColor,
    medianColor: null,
    itemRadius: 0,
    itemStyle: 'circle',
    itemBackgroundColor: Chart.defaults.global.elements.rectangle.backgroundColor,
    itemBorderColor: Chart.defaults.global.elements.rectangle.borderColor,
    hitPadding: 2
  });
  var ArrayElementBase = Chart.Element.extend({
    isVertical: function isVertical() {
      return this._view.width !== undefined;
    },
    draw: function draw() {// abstract
    },
    _drawItems: function _drawItems(vm, container, ctx, vert) {
      if (vm.itemRadius <= 0 || !container.items || container.items.length <= 0) {
        return;
      }

      ctx.save();
      ctx.strokeStle = vm.itemBorderColor;
      ctx.fillStyle = vm.itemBackgroundColor; // jitter based on random data
      // use the datesetindex and index to initialize the random number generator

      var random = rnd(this._datasetIndex * 1000 + this._index);

      if (vert) {
        container.items.forEach(function (v) {
          Chart.canvasHelpers.drawPoint(ctx, vm.itemStyle, vm.itemRadius, vm.x - vm.width / 2 + random() * vm.width, v);
        });
      } else {
        container.items.forEach(function (v) {
          Chart.canvasHelpers.drawPoint(ctx, vm.itemStyle, vm.itemRadius, v, vm.y - vm.height / 2 + random() * vm.height);
        });
      }

      ctx.restore();
    },
    _drawOutliers: function _drawOutliers(vm, container, ctx, vert) {
      if (!container.outliers) {
        return;
      }

      ctx.fillStyle = vm.outlierColor;
      ctx.beginPath();

      if (vert) {
        container.outliers.forEach(function (v) {
          ctx.arc(vm.x, v, vm.outlierRadius, 0, Math.PI * 2);
        });
      } else {
        container.outliers.forEach(function (v) {
          ctx.arc(v, vm.y, vm.outlierRadius, 0, Math.PI * 2);
        });
      }

      ctx.fill();
      ctx.closePath();
    },
    _getBounds: function _getBounds() {
      // abstract
      return {
        left: 0,
        top: 0,
        right: 0,
        bottom: 0
      };
    },
    _getHitBounds: function _getHitBounds() {
      var padding = this._view.hitPadding;

      var b = this._getBounds();

      return {
        left: b.left - padding,
        top: b.top - padding,
        right: b.right + padding,
        bottom: b.bottom + padding
      };
    },
    height: function height() {
      return 0; // abstract
    },
    inRange: function inRange(mouseX, mouseY) {
      if (!this._view) {
        return false;
      }

      var bounds = this._getHitBounds();

      return mouseX >= bounds.left && mouseX <= bounds.right && mouseY >= bounds.top && mouseY <= bounds.bottom;
    },
    inLabelRange: function inLabelRange(mouseX, mouseY) {
      if (!this._view) {
        return false;
      }

      var bounds = this._getHitBounds();

      if (this.isVertical()) {
        return mouseX >= bounds.left && mouseX <= bounds.right;
      }

      return mouseY >= bounds.top && mouseY <= bounds.bottom;
    },
    inXRange: function inXRange(mouseX) {
      var bounds = this._getHitBounds();

      return mouseX >= bounds.left && mouseX <= bounds.right;
    },
    inYRange: function inYRange(mouseY) {
      var bounds = this._getHitBounds();

      return mouseY >= bounds.top && mouseY <= bounds.bottom;
    },
    getCenterPoint: function getCenterPoint() {
      var _this$_view = this._view,
          x = _this$_view.x,
          y = _this$_view.y;
      return {
        x: x,
        y: y
      };
    },
    getArea: function getArea() {
      return 0; // abstract
    },
    tooltipPosition_: function tooltipPosition_() {
      return this.getCenterPoint();
    }
  });

  Chart.defaults.global.elements.boxandwhiskers = _objectSpread({}, defaults);

  function transitionBoxPlot(start, view, model, ease) {
    var keys = Object.keys(model);

    for (var _i = 0; _i < keys.length; _i++) {
      var key = keys[_i];
      var target = model[key];
      var origin = start[key];

      if (origin === target) {
        continue;
      }

      if (typeof target === 'number') {
        view[key] = origin + (target - origin) * ease;
        continue;
      }

      if (Array.isArray(target)) {
        var v = view[key];
        var common = Math.min(target.length, origin.length);

        for (var i = 0; i < common; ++i) {
          v[i] = origin[i] + (target[i] - origin[i]) * ease;
        }
      }
    }
  }

  var BoxAndWiskers = Chart.elements.BoxAndWhiskers = ArrayElementBase.extend({
    transition: function transition(ease) {
      var r = Chart.Element.prototype.transition.call(this, ease);
      var model = this._model;
      var start = this._start;
      var view = this._view; // No animation -> No Transition

      if (!model || ease === 1) {
        return r;
      }

      if (start.boxplot == null) {
        return r; // model === view -> not copied
      } // create deep copy to avoid alternation


      if (model.boxplot === view.boxplot) {
        view.boxplot = Chart.helpers.clone(view.boxplot);
      }

      transitionBoxPlot(start.boxplot, view.boxplot, model.boxplot, ease);
      return r;
    },
    draw: function draw() {
      var ctx = this._chart.ctx;
      var vm = this._view;
      var boxplot = vm.boxplot;
      var vert = this.isVertical();

      this._drawItems(vm, boxplot, ctx, vert);

      ctx.save();
      ctx.fillStyle = vm.backgroundColor;
      ctx.strokeStyle = vm.borderColor;
      ctx.lineWidth = vm.borderWidth;

      this._drawBoxPlot(vm, boxplot, ctx, vert);

      this._drawOutliers(vm, boxplot, ctx, vert);

      ctx.restore();
    },
    _drawBoxPlot: function _drawBoxPlot(vm, boxplot, ctx, vert) {
      if (vert) {
        this._drawBoxPlotVert(vm, boxplot, ctx);
      } else {
        this._drawBoxPlotHoriz(vm, boxplot, ctx);
      }
    },
    _drawBoxPlotVert: function _drawBoxPlotVert(vm, boxplot, ctx) {
      var x = vm.x;
      var width = vm.width;
      var x0 = x - width / 2; // Draw the q1>q3 box

      if (boxplot.q3 > boxplot.q1) {
        ctx.fillRect(x0, boxplot.q1, width, boxplot.q3 - boxplot.q1);
      } else {
        ctx.fillRect(x0, boxplot.q3, width, boxplot.q1 - boxplot.q3);
      } // Draw the median line


      ctx.save();

      if (vm.medianColor) {
        ctx.strokeStyle = vm.medianColor;
      }

      ctx.beginPath();
      ctx.moveTo(x0, boxplot.median);
      ctx.lineTo(x0 + width, boxplot.median);
      ctx.closePath();
      ctx.stroke();
      ctx.restore(); // Draw the border around the main q1>q3 box

      if (boxplot.q3 > boxplot.q1) {
        ctx.strokeRect(x0, boxplot.q1, width, boxplot.q3 - boxplot.q1);
      } else {
        ctx.strokeRect(x0, boxplot.q3, width, boxplot.q1 - boxplot.q3);
      } // Draw the whiskers


      ctx.beginPath();
      ctx.moveTo(x0, boxplot.whiskerMin);
      ctx.lineTo(x0 + width, boxplot.whiskerMin);
      ctx.moveTo(x, boxplot.whiskerMin);
      ctx.lineTo(x, boxplot.q1);
      ctx.moveTo(x0, boxplot.whiskerMax);
      ctx.lineTo(x0 + width, boxplot.whiskerMax);
      ctx.moveTo(x, boxplot.whiskerMax);
      ctx.lineTo(x, boxplot.q3);
      ctx.closePath();
      ctx.stroke();
    },
    _drawBoxPlotHoriz: function _drawBoxPlotHoriz(vm, boxplot, ctx) {
      var y = vm.y;
      var height = vm.height;
      var y0 = y - height / 2; // Draw the q1>q3 box

      if (boxplot.q3 > boxplot.q1) {
        ctx.fillRect(boxplot.q1, y0, boxplot.q3 - boxplot.q1, height);
      } else {
        ctx.fillRect(boxplot.q3, y0, boxplot.q1 - boxplot.q3, height);
      } // Draw the median line


      ctx.save();

      if (vm.medianColor) {
        ctx.strokeStyle = vm.medianColor;
      }

      ctx.beginPath();
      ctx.moveTo(boxplot.median, y0);
      ctx.lineTo(boxplot.median, y0 + height);
      ctx.closePath();
      ctx.stroke();
      ctx.restore(); // Draw the border around the main q1>q3 box

      if (boxplot.q3 > boxplot.q1) {
        ctx.strokeRect(boxplot.q1, y0, boxplot.q3 - boxplot.q1, height);
      } else {
        ctx.strokeRect(boxplot.q3, y0, boxplot.q1 - boxplot.q3, height);
      } // Draw the whiskers


      ctx.beginPath();
      ctx.moveTo(boxplot.whiskerMin, y0);
      ctx.lineTo(boxplot.whiskerMin, y0 + height);
      ctx.moveTo(boxplot.whiskerMin, y);
      ctx.lineTo(boxplot.q1, y);
      ctx.moveTo(boxplot.whiskerMax, y0);
      ctx.lineTo(boxplot.whiskerMax, y0 + height);
      ctx.moveTo(boxplot.whiskerMax, y);
      ctx.lineTo(boxplot.q3, y);
      ctx.closePath();
      ctx.stroke();
    },
    _getBounds: function _getBounds() {
      var vm = this._view;
      var vert = this.isVertical();
      var boxplot = vm.boxplot;

      if (!boxplot) {
        return {
          left: 0,
          top: 0,
          right: 0,
          bottom: 0
        };
      }

      if (vert) {
        var x = vm.x,
            width = vm.width;
        var x0 = x - width / 2;
        return {
          left: x0,
          top: boxplot.whiskerMax,
          right: x0 + width,
          bottom: boxplot.whiskerMin
        };
      }

      var y = vm.y,
          height = vm.height;
      var y0 = y - height / 2;
      return {
        left: boxplot.whiskerMin,
        top: y0,
        right: boxplot.whiskerMax,
        bottom: y0 + height
      };
    },
    height: function height() {
      var vm = this._view;
      return vm.base - Math.min(vm.boxplot.q1, vm.boxplot.q3);
    },
    getArea: function getArea() {
      var vm = this._view;
      var iqr = Math.abs(vm.boxplot.q3 - vm.boxplot.q1);

      if (this.isVertical()) {
        return iqr * vm.width;
      }

      return iqr * vm.height;
    }
  });

  Chart.defaults.global.elements.violin = _objectSpread({
    points: 100
  }, defaults);

  function transitionViolin(start, view, model, ease) {
    var keys = Object.keys(model);

    for (var _i = 0; _i < keys.length; _i++) {
      var key = keys[_i];
      var target = model[key];
      var origin = start[key];

      if (origin === target) {
        continue;
      }

      if (typeof target === 'number') {
        view[key] = origin + (target - origin) * ease;
        continue;
      }

      if (key === 'coords') {
        var v = view[key];
        var common = Math.min(target.length, origin.length);

        for (var i = 0; i < common; ++i) {
          v[i].v = origin[i].v + (target[i].v - origin[i].v) * ease;
          v[i].estimate = origin[i].estimate + (target[i].estimate - origin[i].estimate) * ease;
        }
      }
    }
  }

  var Violin = Chart.elements.Violin = ArrayElementBase.extend({
    transition: function transition(ease) {
      var r = Chart.Element.prototype.transition.call(this, ease);
      var model = this._model;
      var start = this._start;
      var view = this._view; // No animation -> No Transition

      if (!model || ease === 1) {
        return r;
      }

      if (start.violin == null) {
        return r; // model === view -> not copied
      } // create deep copy to avoid alternation


      if (model.violin === view.violin) {
        view.violin = Chart.helpers.clone(view.violin);
      }

      transitionViolin(start.violin, view.violin, model.violin, ease);
      return r;
    },
    draw: function draw() {
      var ctx = this._chart.ctx;
      var vm = this._view;
      var violin = vm.violin;
      var vert = this.isVertical();

      this._drawItems(vm, violin, ctx, vert);

      ctx.save();
      ctx.fillStyle = vm.backgroundColor;
      ctx.strokeStyle = vm.borderColor;
      ctx.lineWidth = vm.borderWidth;
      var coords = violin.coords;
      Chart.canvasHelpers.drawPoint(ctx, 'rectRot', 5, vm.x, vm.y);
      ctx.stroke();
      ctx.beginPath();

      if (vert) {
        var x = vm.x;
        var width = vm.width;
        var factor = width / 2 / violin.maxEstimate;
        ctx.moveTo(x, violin.min);
        coords.forEach(function (_ref) {
          var v = _ref.v,
              estimate = _ref.estimate;
          ctx.lineTo(x - estimate * factor, v);
        });
        ctx.lineTo(x, violin.max);
        ctx.moveTo(x, violin.min);
        coords.forEach(function (_ref2) {
          var v = _ref2.v,
              estimate = _ref2.estimate;
          ctx.lineTo(x + estimate * factor, v);
        });
        ctx.lineTo(x, violin.max);
      } else {
        var y = vm.y;
        var height = vm.height;

        var _factor = height / 2 / violin.maxEstimate;

        ctx.moveTo(violin.min, y);
        coords.forEach(function (_ref3) {
          var v = _ref3.v,
              estimate = _ref3.estimate;
          ctx.lineTo(v, y - estimate * _factor);
        });
        ctx.lineTo(violin.max, y);
        ctx.moveTo(violin.min, y);
        coords.forEach(function (_ref4) {
          var v = _ref4.v,
              estimate = _ref4.estimate;
          ctx.lineTo(v, y + estimate * _factor);
        });
        ctx.lineTo(violin.max, y);
      }

      ctx.stroke();
      ctx.fill();
      ctx.closePath();

      this._drawOutliers(vm, violin, ctx, vert);

      ctx.restore();
    },
    _getBounds: function _getBounds() {
      var vm = this._view;
      var vert = this.isVertical();
      var violin = vm.violin;

      if (vert) {
        var x = vm.x,
            width = vm.width;
        var x0 = x - width / 2;
        return {
          left: x0,
          top: violin.max,
          right: x0 + width,
          bottom: violin.min
        };
      }

      var y = vm.y,
          height = vm.height;
      var y0 = y - height / 2;
      return {
        left: violin.min,
        top: y0,
        right: violin.max,
        bottom: y0 + height
      };
    },
    height: function height() {
      var vm = this._view;
      return vm.base - Math.min(vm.violin.min, vm.violin.max);
    },
    getArea: function getArea() {
      var vm = this._view;
      var iqr = Math.abs(vm.violin.max - vm.violin.min);

      if (this.isVertical()) {
        return iqr * vm.width;
      }

      return iqr * vm.height;
    }
  });

  var verticalDefaults = {
    scales: {
      yAxes: [{
        type: 'arrayLinear'
      }]
    }
  };
  var horizontalDefaults = {
    scales: {
      xAxes: [{
        type: 'arrayLinear'
      }]
    }
  };
  var array = {
    _elementOptions: function _elementOptions() {
      return {};
    },
    updateElement: function updateElement(elem, index, reset) {
      var dataset = this.getDataset();
      var custom = elem.custom || {};

      var options = this._elementOptions();

      Chart.controllers.bar.prototype.updateElement.call(this, elem, index, reset);
      var resolve = Chart.helpers.options.resolve;
      var keys = ['outlierRadius', 'itemRadius', 'itemStyle', 'itemBackgroundColor', 'itemBorderColor', 'outlierColor', 'medianColor', 'hitPadding']; // Scriptable options

      var context = {
        chart: this.chart,
        dataIndex: index,
        dataset: dataset,
        datasetIndex: this.index
      };
      keys.forEach(function (item) {
        elem._model[item] = resolve([custom[item], dataset[item], options[item]], context, index);
      });
    },
    _calculateCommonModel: function _calculateCommonModel(r, data, container, scale) {
      if (container.outliers) {
        r.outliers = container.outliers.map(function (d) {
          return scale.getPixelForValue(Number(d));
        });
      }

      if (Array.isArray(data)) {
        r.items = data.map(function (d) {
          return scale.getPixelForValue(Number(d));
        });
      }
    }
  };

  var defaults$1 = {
    tooltips: {
      callbacks: {
        label: function label(item, data) {
          var datasetLabel = data.datasets[item.datasetIndex].label || '';
          var value = data.datasets[item.datasetIndex].data[item.index];
          var b = asBoxPlotStats(value);
          var label = "".concat(datasetLabel, " ").concat(typeof item.xLabel === 'string' ? item.xLabel : item.yLabel);

          if (!b) {
            return label + 'NaN';
          }

          return "".concat(label, " (min: ").concat(b.min, ", q1: ").concat(b.q1, ", median: ").concat(b.median, ", q3: ").concat(b.q3, ", max: ").concat(b.max, ")");
        }
      }
    }
  };
  Chart.defaults.boxplot = Chart.helpers.merge({}, [Chart.defaults.bar, verticalDefaults, defaults$1]);
  Chart.defaults.horizontalBoxplot = Chart.helpers.merge({}, [Chart.defaults.horizontalBar, horizontalDefaults, defaults$1]);

  var boxplot = _objectSpread({}, array, {
    dataElementType: Chart.elements.BoxAndWhiskers,
    _elementOptions: function _elementOptions() {
      return this.chart.options.elements.boxandwhiskers;
    },

    /**
     * @private
     */
    _updateElementGeometry: function _updateElementGeometry(elem, index, reset) {
      Chart.controllers.bar.prototype._updateElementGeometry.call(this, elem, index, reset);

      elem._model.boxplot = this._calculateBoxPlotValuesPixels(this.index, index);
    },

    /**
     * @private
     */
    _calculateBoxPlotValuesPixels: function _calculateBoxPlotValuesPixels(datasetIndex, index) {
      var scale = this._getValueScale();

      var data = this.chart.data.datasets[datasetIndex].data[index];

      if (!data) {
        return null;
      }

      var v = asBoxPlotStats(data);
      var r = {};
      Object.keys(v).forEach(function (key) {
        if (key !== 'outliers') {
          r[key] = scale.getPixelForValue(Number(v[key]));
        }
      });

      this._calculateCommonModel(r, data, v, scale);

      return r;
    }
  });
  /**
   * This class is based off controller.bar.js from the upstream Chart.js library
   */


  var BoxPlot = Chart.controllers.boxplot = Chart.controllers.bar.extend(boxplot);
  var HorizontalBoxPlot = Chart.controllers.horizontalBoxplot = Chart.controllers.horizontalBar.extend(boxplot);

  var defaults$2 = {};
  Chart.defaults.violin = Chart.helpers.merge({}, [Chart.defaults.bar, verticalDefaults, defaults$2]);
  Chart.defaults.horizontalViolin = Chart.helpers.merge({}, [Chart.defaults.horizontalBar, horizontalDefaults, defaults$2]);

  var controller = _objectSpread({}, array, {
    dataElementType: Chart.elements.Violin,
    _elementOptions: function _elementOptions() {
      return this.chart.options.elements.violin;
    },

    /**
     * @private
     */
    _updateElementGeometry: function _updateElementGeometry(elem, index, reset) {
      Chart.controllers.bar.prototype._updateElementGeometry.call(this, elem, index, reset);

      var custom = elem.custom || {};

      var options = this._elementOptions();

      elem._model.violin = this._calculateViolinValuesPixels(this.index, index, custom.points !== undefined ? custom.points : options.points);
    },

    /**
     * @private
     */
    _calculateViolinValuesPixels: function _calculateViolinValuesPixels(datasetIndex, index, points) {
      var scale = this._getValueScale();

      var data = this.chart.data.datasets[datasetIndex].data[index];
      var violin = asViolinStats(data);
      var range = violin.max - violin.min;
      var samples = [];
      var inc = range / points;

      for (var v = violin.min; v <= violin.max; v += inc) {
        samples.push(v);
      }

      if (samples[samples.length - 1] !== violin.max) {
        samples.push(violin.max);
      }

      var coords = violin.coords || violin.kde(samples).map(function (v) {
        return {
          v: v[0],
          estimate: v[1]
        };
      });
      var r = {
        min: scale.getPixelForValue(violin.min),
        max: scale.getPixelForValue(violin.max),
        median: scale.getPixelForValue(violin.median),
        coords: coords.map(function (_ref) {
          var v = _ref.v,
              estimate = _ref.estimate;
          return {
            v: scale.getPixelForValue(v),
            estimate: estimate
          };
        }),
        maxEstimate: coords.reduce(function (a, d) {
          return Math.max(a, d.estimate);
        }, Number.NEGATIVE_INFINITY)
      };

      this._calculateCommonModel(r, data, violin, scale);

      return r;
    }
  });
  /**
   * This class is based off controller.bar.js from the upstream Chart.js library
   */


  var Violin$1 = Chart.controllers.violin = Chart.controllers.bar.extend(controller);
  var HorizontalViolin = Chart.controllers.horizontalViolin = Chart.controllers.horizontalBar.extend(controller);

  var helpers = Chart.helpers;
  var ArrayLinearScaleOptions = helpers.merge({}, [commonScaleOptions, Chart.scaleService.getScaleDefaults('linear')]);
  var ArrayLinearScale = Chart.scaleService.getScaleConstructor('linear').extend({
    getRightValue: function getRightValue$$1(rawValue) {
      return Chart.LinearScaleBase.prototype.getRightValue.call(this, getRightValue(rawValue));
    },
    determineDataLimits: function determineDataLimits() {
      commonDataLimits.call(this); // Common base implementation to handle ticks.min, ticks.max, ticks.beginAtZero

      this.handleTickRangeOptions();
    }
  });
  Chart.scaleService.registerScaleType('arrayLinear', ArrayLinearScale, ArrayLinearScaleOptions);

  var helpers$1 = Chart.helpers;
  var ArrayLogarithmicScaleOptions = helpers$1.merge({}, [commonScaleOptions, Chart.scaleService.getScaleDefaults('logarithmic')]);
  var ArrayLogarithmicScale = Chart.scaleService.getScaleConstructor('logarithmic').extend({
    getRightValue: function getRightValue$$1(rawValue) {
      return Chart.LinearScaleBase.prototype.getRightValue.call(this, getRightValue(rawValue));
    },
    determineDataLimits: function determineDataLimits() {
      var _this = this;

      // Add whitespace around bars. Axis shouldn't go exactly from min to max
      var tickOpts = this.options.ticks;
      this.minNotZero = null;
      commonDataLimits.call(this, function (boxPlot) {
        var value = boxPlot[tickOpts.minStats];

        if (value !== 0 && (_this.minNotZero === null || value < _this.minNotZero)) {
          _this.minNotZero = value;
        }
      });
      this.min = helpers$1.valueOrDefault(tickOpts.min, this.min - this.min * 0.05);
      this.max = helpers$1.valueOrDefault(tickOpts.max, this.max + this.max * 0.05);

      if (this.min === this.max) {
        if (this.min !== 0 && this.min !== null) {
          this.min = Math.pow(10, Math.floor(helpers$1.log10(this.min)) - 1);
          this.max = Math.pow(10, Math.floor(helpers$1.log10(this.max)) + 1);
        } else {
          this.min = 1;
          this.max = 10;
        }
      }
    }
  });
  Chart.scaleService.registerScaleType('arrayLogarithmic', ArrayLogarithmicScale, ArrayLogarithmicScaleOptions);

  exports.BoxAndWhiskers = BoxAndWiskers;
  exports.Violin = Violin;
  exports.ArrayLinearScale = ArrayLinearScale;
  exports.ArrayLogarithmicScale = ArrayLogarithmicScale;
  exports.BoxPlot = BoxPlot;
  exports.HorizontalBoxPlot = HorizontalBoxPlot;
  exports.HorizontalViolin = HorizontalViolin;

  Object.defineProperty(exports, '__esModule', { value: true });

}));


// box BoxPlot library
// (function (global, factory) {
//   typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports, require('chart.js')) :
//   typeof define === 'function' && define.amd ? define(['exports', 'chart.js'], factory) :
//   (global = global || self, factory(global.ChartBoxPlot = {}, global.Chart));
// }(this, function (exports, Chart) { 'use strict';
//
//   function _defineProperty(obj, key, value) {
//     if (key in obj) {
//       Object.defineProperty(obj, key, {
//         value: value,
//         enumerable: true,
//         configurable: true,
//         writable: true
//       });
//     } else {
//       obj[key] = value;
//     }
//
//     return obj;
//   }
//
//   function _objectSpread(target) {
//     for (var i = 1; i < arguments.length; i++) {
//       var source = arguments[i] != null ? arguments[i] : {};
//       var ownKeys = Object.keys(source);
//
//       if (typeof Object.getOwnPropertySymbols === 'function') {
//         ownKeys = ownKeys.concat(Object.getOwnPropertySymbols(source).filter(function (sym) {
//           return Object.getOwnPropertyDescriptor(source, sym).enumerable;
//         }));
//       }
//
//       ownKeys.forEach(function (key) {
//         _defineProperty(target, key, source[key]);
//       });
//     }
//
//     return target;
//   }
//
//   function _slicedToArray(arr, i) {
//     return _arrayWithHoles(arr) || _iterableToArrayLimit(arr, i) || _nonIterableRest();
//   }
//
//   function _arrayWithHoles(arr) {
//     if (Array.isArray(arr)) return arr;
//   }
//
//   function _iterableToArrayLimit(arr, i) {
//     var _arr = [];
//     var _n = true;
//     var _d = false;
//     var _e = undefined;
//
//     try {
//       for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) {
//         _arr.push(_s.value);
//
//         if (i && _arr.length === i) break;
//       }
//     } catch (err) {
//       _d = true;
//       _e = err;
//     } finally {
//       try {
//         if (!_n && _i["return"] != null) _i["return"]();
//       } finally {
//         if (_d) throw _e;
//       }
//     }
//
//     return _arr;
//   }
//
//   function _nonIterableRest() {
//     throw new TypeError("Invalid attempt to destructure non-iterable instance");
//   }
//
//   function ascending(a, b) {
//     return a - b;
//   }
//
//   function quantiles(d, quantiles) {
//     d = d.slice().sort(ascending);
//     var n_1 = d.length - 1;
//     return quantiles.map(function (q) {
//       if (q === 0) return d[0];else if (q === 1) return d[n_1];
//       var index = 1 + q * n_1,
//           lo = Math.floor(index),
//           h = index - lo,
//           a = d[lo - 1];
//       return h === 0 ? a : a + h * (d[lo] - a);
//     });
//   }
//
//   // See <http://en.wikipedia.org/wiki/Kernel_(statistics)>.
//   function gaussian(u) {
//     return 1 / Math.sqrt(2 * Math.PI) * Math.exp(-.5 * u * u);
//   }
//
//   // Welford's algorithm.
//   function mean(x) {
//     var n = x.length;
//     if (n === 0) return NaN;
//     var m = 0,
//         i = -1;
//
//     while (++i < n) m += (x[i] - m) / (i + 1);
//
//     return m;
//   }
//
//   // Also known as the sample variance, where the denominator is n - 1.
//
//   function variance(x) {
//     var n = x.length;
//     if (n < 1) return NaN;
//     if (n === 1) return 0;
//     var mean$$1 = mean(x),
//         i = -1,
//         s = 0;
//
//     while (++i < n) {
//       var v = x[i] - mean$$1;
//       s += v * v;
//     }
//
//     return s / (n - 1);
//   }
//
//   function iqr(x) {
//     var quartiles = quantiles(x, [.25, .75]);
//     return quartiles[1] - quartiles[0];
//   }
//
//   // Visualization. Wiley.
//
//   function nrd(x) {
//     var h = iqr(x) / 1.34;
//     return 1.06 * Math.min(Math.sqrt(variance(x)), h) * Math.pow(x.length, -1 / 5);
//   }
//
//   function functor(v) {
//     return typeof v === "function" ? v : function () {
//       return v;
//     };
//   }
//
//   function kde() {
//     var kernel = gaussian,
//         sample = [],
//         bandwidth = nrd;
//
//     function kde(points, i) {
//       var bw = bandwidth.call(this, sample);
//       return points.map(function (x) {
//         var i = -1,
//             y = 0,
//             n = sample.length;
//
//         while (++i < n) {
//           y += kernel((x - sample[i]) / bw);
//         }
//
//         return [x, y / bw / n];
//       });
//     }
//
//     kde.kernel = function (x) {
//       if (!arguments.length) return kernel;
//       kernel = x;
//       return kde;
//     };
//
//     kde.sample = function (x) {
//       if (!arguments.length) return sample;
//       sample = x;
//       return kde;
//     };
//
//     kde.bandwidth = function (x) {
//       if (!arguments.length) return bandwidth;
//       bandwidth = functor(x);
//       return kde;
//     };
//
//     return kde;
//   }
//
//   function extent(arr) {
//     return arr.reduce(function (acc, v) {
//       return [Math.min(acc[0], v), Math.max(acc[1], v)];
//     }, [Number.POSITIVE_INFINITY, Number.NEGATIVE_INFINITY]);
//   }
//
//   function whiskers(boxplot, arr) {
//     var iqr = boxplot.q3 - boxplot.q1; // since top left is max
//
//     var whiskerMin = Math.max(boxplot.min, boxplot.q1 - iqr);
//     var whiskerMax = Math.min(boxplot.max, boxplot.q3 + iqr);
//
//     if (Array.isArray(arr)) {
//       // compute the closest real element
//       for (var i = 0; i < arr.length; i++) {
//         var v = arr[i];
//
//         if (v >= whiskerMin) {
//           whiskerMin = v;
//           break;
//         }
//       }
//
//       for (var _i = arr.length - 1; _i >= 0; _i--) {
//         var _v = arr[_i];
//
//         if (_v <= whiskerMax) {
//           whiskerMax = _v;
//           break;
//         }
//       }
//     }
//
//     return {
//       whiskerMin: whiskerMin,
//       whiskerMax: whiskerMax
//     };
//   }
//   function boxplotStats(arr) {
//     // console.assert(Array.isArray(arr));
//     if (arr.length === 0) {
//       return {
//         min: NaN,
//         max: NaN,
//         median: NaN,
//         q1: NaN,
//         q3: NaN,
//         whiskerMin: NaN,
//         whiskerMax: NaN,
//         outliers: []
//       };
//     }
//
//     arr = arr.filter(function (v) {
//       return typeof v === 'number' && !isNaN(v);
//     });
//     arr.sort(function (a, b) {
//       return a - b;
//     });
//
//     var _quantiles = quantiles(arr, [0.5, 0.25, 0.75]),
//         _quantiles2 = _slicedToArray(_quantiles, 3),
//         median = _quantiles2[0],
//         q1 = _quantiles2[1],
//         q3 = _quantiles2[2];
//
//     var minmax = extent(arr);
//     var base = {
//       min: minmax[0],
//       max: minmax[1],
//       median: median,
//       q1: q1,
//       q3: q3,
//       outliers: []
//     };
//
//     var _whiskers = whiskers(base, arr),
//         whiskerMin = _whiskers.whiskerMin,
//         whiskerMax = _whiskers.whiskerMax;
//
//     base.outliers = arr.filter(function (v) {
//       return v < whiskerMin || v > whiskerMax;
//     });
//     base.whiskerMin = whiskerMin;
//     base.whiskerMax = whiskerMax;
//     return base;
//   }
//   function violinStats(arr) {
//     // console.assert(Array.isArray(arr));
//     if (arr.length === 0) {
//       return {
//         outliers: []
//       };
//     }
//
//     arr = arr.filter(function (v) {
//       return typeof v === 'number' && !isNaN(v);
//     });
//     arr.sort(function (a, b) {
//       return a - b;
//     });
//     var minmax = extent(arr);
//     return {
//       min: minmax[0],
//       max: minmax[1],
//       median: quantiles(arr, [0.5])[0],
//       kde: kde().sample(arr)
//     };
//   }
//   function asBoxPlotStats(value) {
//     if (!value) {
//       return null;
//     }
//
//     if (typeof value.median === 'number' && typeof value.q1 === 'number' && typeof value.q3 === 'number') {
//       // sounds good, check for helper
//       if (typeof value.whiskerMin === 'undefined') {
//         var _whiskers2 = whiskers(value, Array.isArray(value.items) ? value.items.slice().sort(function (a, b) {
//           return a - b;
//         }) : null),
//             whiskerMin = _whiskers2.whiskerMin,
//             whiskerMax = _whiskers2.whiskerMax;
//
//         value.whiskerMin = whiskerMin;
//         value.whiskerMax = whiskerMax;
//       }
//
//       return value;
//     }
//
//     if (!Array.isArray(value)) {
//       return undefined;
//     }
//
//     if (value.__stats === undefined) {
//       value.__stats = boxplotStats(value);
//     }
//
//     return value.__stats;
//   }
//   function asViolinStats(value) {
//     if (!value) {
//       return null;
//     }
//
//     if (typeof value.median === 'number' && (typeof value.kde === 'function' || Array.isArray(value.coords))) {
//       return value;
//     }
//
//     if (!Array.isArray(value)) {
//       return undefined;
//     }
//
//     if (value.__kde === undefined) {
//       value.__kde = violinStats(value);
//     }
//
//     return value.__kde;
//   }
//   function asValueStats(value, minStats, maxStats) {
//     if (typeof value[minStats] === 'number' && typeof value[maxStats] === 'number') {
//       return value;
//     }
//
//     if (!Array.isArray(value) || value.length === 0) {
//       return undefined;
//     }
//
//     return asBoxPlotStats(value);
//   }
//   function getRightValue(rawValue) {
//     if (!rawValue) {
//       return rawValue;
//     }
//
//     if (typeof rawValue === 'number' || typeof rawValue === 'string') {
//       return Number(rawValue);
//     }
//
//     var b = asBoxPlotStats(rawValue);
//     return b ? b.median : rawValue;
//   }
//   var commonScaleOptions = {
//     ticks: {
//       minStats: 'min',
//       maxStats: 'max'
//     }
//   };
//   function commonDataLimits(extraCallback) {
//     var _this = this;
//
//     var chart = this.chart;
//     var isHorizontal = this.isHorizontal();
//     var tickOpts = this.options.ticks;
//     var minStats = tickOpts.minStats;
//     var maxStats = tickOpts.maxStats;
//
//     var matchID = function matchID(meta) {
//       return isHorizontal ? meta.xAxisID === _this.id : meta.yAxisID === _this.id;
//     }; // First Calculate the range
//
//
//     this.min = null;
//     this.max = null; // Regular charts use x, y values
//     // For the boxplot chart we have rawValue.min and rawValue.max for each point
//
//     chart.data.datasets.forEach(function (d, i) {
//       var meta = chart.getDatasetMeta(i);
//
//       if (!chart.isDatasetVisible(i) || !matchID(meta)) {
//         return;
//       }
//
//       d.data.forEach(function (value, j) {
//         if (!value || meta.data[j].hidden) {
//           return;
//         }
//
//         var stats = asValueStats(value, minStats, maxStats);
//
//         if (!stats) {
//           return;
//         }
//
//         if (_this.min === null || stats[minStats] < _this.min) {
//           _this.min = stats[minStats];
//         }
//
//         if (_this.max === null || stats[maxStats] > _this.max) {
//           _this.max = stats[maxStats];
//         }
//
//         if (extraCallback) {
//           extraCallback(stats);
//         }
//       });
//     });
//   }
//   function rnd(seed) {
//     // Adapted from http://indiegamr.com/generate-repeatable-random-numbers-in-js/
//     if (seed === undefined) {
//       seed = Date.now();
//     }
//
//     return function () {
//       seed = (seed * 9301 + 49297) % 233280;
//       return seed / 233280;
//     };
//   }
//
//   var defaults = _objectSpread({}, Chart.defaults.global.elements.rectangle, {
//     borderWidth: 1,
//     outlierRadius: 2,
//     outlierColor: Chart.defaults.global.elements.rectangle.backgroundColor,
//     medianColor: null,
//     itemRadius: 0,
//     itemStyle: 'circle',
//     itemBackgroundColor: Chart.defaults.global.elements.rectangle.backgroundColor,
//     itemBorderColor: Chart.defaults.global.elements.rectangle.borderColor,
//     hitPadding: 2
//   });
//   var ArrayElementBase = Chart.Element.extend({
//     isVertical: function isVertical() {
//       return this._view.width !== undefined;
//     },
//     draw: function draw() {// abstract
//     },
//     _drawItems: function _drawItems(vm, container, ctx, vert) {
//       if (vm.itemRadius <= 0 || !container.items || container.items.length <= 0) {
//         return;
//       }
//
//       ctx.save();
//       ctx.strokeStle = vm.itemBorderColor;
//       ctx.fillStyle = vm.itemBackgroundColor; // jitter based on random data
//       // use the datesetindex and index to initialize the random number generator
//
//       var random = rnd(this._datasetIndex * 1000 + this._index);
//
//       if (vert) {
//         container.items.forEach(function (v) {
//           Chart.canvasHelpers.drawPoint(ctx, vm.itemStyle, vm.itemRadius, vm.x - vm.width / 2 + random() * vm.width, v);
//         });
//       } else {
//         container.items.forEach(function (v) {
//           Chart.canvasHelpers.drawPoint(ctx, vm.itemStyle, vm.itemRadius, v, vm.y - vm.height / 2 + random() * vm.height);
//         });
//       }
//
//       ctx.restore();
//     },
//     _drawOutliers: function _drawOutliers(vm, container, ctx, vert) {
//       if (!container.outliers) {
//         return;
//       }
//
//       ctx.fillStyle = vm.outlierColor;
//       ctx.beginPath();
//
//       if (vert) {
//         container.outliers.forEach(function (v) {
//           ctx.arc(vm.x, v, vm.outlierRadius, 0, Math.PI * 2);
//         });
//       } else {
//         container.outliers.forEach(function (v) {
//           ctx.arc(v, vm.y, vm.outlierRadius, 0, Math.PI * 2);
//         });
//       }
//
//       ctx.fill();
//       ctx.closePath();
//     },
//     _getBounds: function _getBounds() {
//       // abstract
//       return {
//         left: 0,
//         top: 0,
//         right: 0,
//         bottom: 0
//       };
//     },
//     _getHitBounds: function _getHitBounds() {
//       var padding = this._view.hitPadding;
//
//       var b = this._getBounds();
//
//       return {
//         left: b.left - padding,
//         top: b.top - padding,
//         right: b.right + padding,
//         bottom: b.bottom + padding
//       };
//     },
//     height: function height() {
//       return 0; // abstract
//     },
//     inRange: function inRange(mouseX, mouseY) {
//       if (!this._view) {
//         return false;
//       }
//
//       var bounds = this._getHitBounds();
//
//       return mouseX >= bounds.left && mouseX <= bounds.right && mouseY >= bounds.top && mouseY <= bounds.bottom;
//     },
//     inLabelRange: function inLabelRange(mouseX, mouseY) {
//       if (!this._view) {
//         return false;
//       }
//
//       var bounds = this._getHitBounds();
//
//       if (this.isVertical()) {
//         return mouseX >= bounds.left && mouseX <= bounds.right;
//       }
//
//       return mouseY >= bounds.top && mouseY <= bounds.bottom;
//     },
//     inXRange: function inXRange(mouseX) {
//       var bounds = this._getHitBounds();
//
//       return mouseX >= bounds.left && mouseX <= bounds.right;
//     },
//     inYRange: function inYRange(mouseY) {
//       var bounds = this._getHitBounds();
//
//       return mouseY >= bounds.top && mouseY <= bounds.bottom;
//     },
//     getCenterPoint: function getCenterPoint() {
//       var _this$_view = this._view,
//           x = _this$_view.x,
//           y = _this$_view.y;
//       return {
//         x: x,
//         y: y
//       };
//     },
//     getArea: function getArea() {
//       return 0; // abstract
//     },
//     tooltipPosition_: function tooltipPosition_() {
//       return this.getCenterPoint();
//     }
//   });
//
//   Chart.defaults.global.elements.boxandwhiskers = _objectSpread({}, defaults);
//
//   function transitionBoxPlot(start, view, model, ease) {
//     var keys = Object.keys(model);
//
//     for (var _i = 0; _i < keys.length; _i++) {
//       var key = keys[_i];
//       var target = model[key];
//       var origin = start[key];
//
//       if (origin === target) {
//         continue;
//       }
//
//       if (typeof target === 'number') {
//         view[key] = origin + (target - origin) * ease;
//         continue;
//       }
//
//       if (Array.isArray(target)) {
//         var v = view[key];
//         var common = Math.min(target.length, origin.length);
//
//         for (var i = 0; i < common; ++i) {
//           v[i] = origin[i] + (target[i] - origin[i]) * ease;
//         }
//       }
//     }
//   }
//
//   var BoxAndWiskers = Chart.elements.BoxAndWhiskers = ArrayElementBase.extend({
//     transition: function transition(ease) {
//       var r = Chart.Element.prototype.transition.call(this, ease);
//       var model = this._model;
//       var start = this._start;
//       var view = this._view; // No animation -> No Transition
//
//       if (!model || ease === 1) {
//         return r;
//       }
//
//       if (start.boxplot == null) {
//         return r; // model === view -> not copied
//       } // create deep copy to avoid alternation
//
//
//       if (model.boxplot === view.boxplot) {
//         view.boxplot = Chart.helpers.clone(view.boxplot);
//       }
//
//       transitionBoxPlot(start.boxplot, view.boxplot, model.boxplot, ease);
//       return r;
//     },
//     draw: function draw() {
//       var ctx = this._chart.ctx;
//       var vm = this._view;
//       var boxplot = vm.boxplot;
//       var vert = this.isVertical();
//
//       this._drawItems(vm, boxplot, ctx, vert);
//
//       ctx.save();
//       ctx.fillStyle = vm.backgroundColor;
//       ctx.strokeStyle = vm.borderColor;
//       ctx.lineWidth = vm.borderWidth;
//
//       this._drawBoxPlot(vm, boxplot, ctx, vert);
//
//       this._drawOutliers(vm, boxplot, ctx, vert);
//
//       ctx.restore();
//     },
//     _drawBoxPlot: function _drawBoxPlot(vm, boxplot, ctx, vert) {
//       if (vert) {
//         this._drawBoxPlotVert(vm, boxplot, ctx);
//       } else {
//         this._drawBoxPlotHoriz(vm, boxplot, ctx);
//       }
//     },
//     _drawBoxPlotVert: function _drawBoxPlotVert(vm, boxplot, ctx) {
//       var x = vm.x;
//       var width = vm.width;
//       var x0 = x - width / 2; // Draw the q1>q3 box
//
//       if (boxplot.q3 > boxplot.q1) {
//         ctx.fillRect(x0, boxplot.q1, width, boxplot.q3 - boxplot.q1);
//       } else {
//         ctx.fillRect(x0, boxplot.q3, width, boxplot.q1 - boxplot.q3);
//       } // Draw the median line
//
//
//       ctx.save();
//
//       if (vm.medianColor) {
//         ctx.strokeStyle = vm.medianColor;
//       }
//
//       ctx.beginPath();
//       ctx.moveTo(x0, boxplot.median);
//       ctx.lineTo(x0 + width, boxplot.median);
//       ctx.closePath();
//       ctx.stroke();
//       ctx.restore(); // Draw the border around the main q1>q3 box
//
//       if (boxplot.q3 > boxplot.q1) {
//         ctx.strokeRect(x0, boxplot.q1, width, boxplot.q3 - boxplot.q1);
//       } else {
//         ctx.strokeRect(x0, boxplot.q3, width, boxplot.q1 - boxplot.q3);
//       } // Draw the whiskers
//
//
//       ctx.beginPath();
//       ctx.moveTo(x0, boxplot.whiskerMin);
//       ctx.lineTo(x0 + width, boxplot.whiskerMin);
//       ctx.moveTo(x, boxplot.whiskerMin);
//       ctx.lineTo(x, boxplot.q1);
//       ctx.moveTo(x0, boxplot.whiskerMax);
//       ctx.lineTo(x0 + width, boxplot.whiskerMax);
//       ctx.moveTo(x, boxplot.whiskerMax);
//       ctx.lineTo(x, boxplot.q3);
//       ctx.closePath();
//       ctx.stroke();
//     },
//     _drawBoxPlotHoriz: function _drawBoxPlotHoriz(vm, boxplot, ctx) {
//       var y = vm.y;
//       var height = vm.height;
//       var y0 = y - height / 2; // Draw the q1>q3 box
//
//       if (boxplot.q3 > boxplot.q1) {
//         ctx.fillRect(boxplot.q1, y0, boxplot.q3 - boxplot.q1, height);
//       } else {
//         ctx.fillRect(boxplot.q3, y0, boxplot.q1 - boxplot.q3, height);
//       } // Draw the median line
//
//
//       ctx.save();
//
//       if (vm.medianColor) {
//         ctx.strokeStyle = vm.medianColor;
//       }
//
//       ctx.beginPath();
//       ctx.moveTo(boxplot.median, y0);
//       ctx.lineTo(boxplot.median, y0 + height);
//       ctx.closePath();
//       ctx.stroke();
//       ctx.restore(); // Draw the border around the main q1>q3 box
//
//       if (boxplot.q3 > boxplot.q1) {
//         ctx.strokeRect(boxplot.q1, y0, boxplot.q3 - boxplot.q1, height);
//       } else {
//         ctx.strokeRect(boxplot.q3, y0, boxplot.q1 - boxplot.q3, height);
//       } // Draw the whiskers
//
//
//       ctx.beginPath();
//       ctx.moveTo(boxplot.whiskerMin, y0);
//       ctx.lineTo(boxplot.whiskerMin, y0 + height);
//       ctx.moveTo(boxplot.whiskerMin, y);
//       ctx.lineTo(boxplot.q1, y);
//       ctx.moveTo(boxplot.whiskerMax, y0);
//       ctx.lineTo(boxplot.whiskerMax, y0 + height);
//       ctx.moveTo(boxplot.whiskerMax, y);
//       ctx.lineTo(boxplot.q3, y);
//       ctx.closePath();
//       ctx.stroke();
//     },
//     _getBounds: function _getBounds() {
//       var vm = this._view;
//       var vert = this.isVertical();
//       var boxplot = vm.boxplot;
//
//       if (!boxplot) {
//         return {
//           left: 0,
//           top: 0,
//           right: 0,
//           bottom: 0
//         };
//       }
//
//       if (vert) {
//         var x = vm.x,
//             width = vm.width;
//         var x0 = x - width / 2;
//         return {
//           left: x0,
//           top: boxplot.whiskerMax,
//           right: x0 + width,
//           bottom: boxplot.whiskerMin
//         };
//       }
//
//       var y = vm.y,
//           height = vm.height;
//       var y0 = y - height / 2;
//       return {
//         left: boxplot.whiskerMin,
//         top: y0,
//         right: boxplot.whiskerMax,
//         bottom: y0 + height
//       };
//     },
//     height: function height() {
//       var vm = this._view;
//       return vm.base - Math.min(vm.boxplot.q1, vm.boxplot.q3);
//     },
//     getArea: function getArea() {
//       var vm = this._view;
//       var iqr = Math.abs(vm.boxplot.q3 - vm.boxplot.q1);
//
//       if (this.isVertical()) {
//         return iqr * vm.width;
//       }
//
//       return iqr * vm.height;
//     }
//   });
//
//   Chart.defaults.global.elements.violin = _objectSpread({
//     points: 100
//   }, defaults);
//
//   function transitionViolin(start, view, model, ease) {
//     var keys = Object.keys(model);
//
//     for (var _i = 0; _i < keys.length; _i++) {
//       var key = keys[_i];
//       var target = model[key];
//       var origin = start[key];
//
//       if (origin === target) {
//         continue;
//       }
//
//       if (typeof target === 'number') {
//         view[key] = origin + (target - origin) * ease;
//         continue;
//       }
//
//       if (key === 'coords') {
//         var v = view[key];
//         var common = Math.min(target.length, origin.length);
//
//         for (var i = 0; i < common; ++i) {
//           v[i].v = origin[i].v + (target[i].v - origin[i].v) * ease;
//           v[i].estimate = origin[i].estimate + (target[i].estimate - origin[i].estimate) * ease;
//         }
//       }
//     }
//   }
//
//   var Violin = Chart.elements.Violin = ArrayElementBase.extend({
//     transition: function transition(ease) {
//       var r = Chart.Element.prototype.transition.call(this, ease);
//       var model = this._model;
//       var start = this._start;
//       var view = this._view; // No animation -> No Transition
//
//       if (!model || ease === 1) {
//         return r;
//       }
//
//       if (start.violin == null) {
//         return r; // model === view -> not copied
//       } // create deep copy to avoid alternation
//
//
//       if (model.violin === view.violin) {
//         view.violin = Chart.helpers.clone(view.violin);
//       }
//
//       transitionViolin(start.violin, view.violin, model.violin, ease);
//       return r;
//     },
//     draw: function draw() {
//       var ctx = this._chart.ctx;
//       var vm = this._view;
//       var violin = vm.violin;
//       var vert = this.isVertical();
//
//       this._drawItems(vm, violin, ctx, vert);
//
//       ctx.save();
//       ctx.fillStyle = vm.backgroundColor;
//       ctx.strokeStyle = vm.borderColor;
//       ctx.lineWidth = vm.borderWidth;
//       var coords = violin.coords;
//       Chart.canvasHelpers.drawPoint(ctx, 'rectRot', 5, vm.x, vm.y);
//       ctx.stroke();
//       ctx.beginPath();
//
//       if (vert) {
//         var x = vm.x;
//         var width = vm.width;
//         var factor = width / 2 / violin.maxEstimate;
//         ctx.moveTo(x, violin.min);
//         coords.forEach(function (_ref) {
//           var v = _ref.v,
//               estimate = _ref.estimate;
//           ctx.lineTo(x - estimate * factor, v);
//         });
//         ctx.lineTo(x, violin.max);
//         ctx.moveTo(x, violin.min);
//         coords.forEach(function (_ref2) {
//           var v = _ref2.v,
//               estimate = _ref2.estimate;
//           ctx.lineTo(x + estimate * factor, v);
//         });
//         ctx.lineTo(x, violin.max);
//       } else {
//         var y = vm.y;
//         var height = vm.height;
//
//         var _factor = height / 2 / violin.maxEstimate;
//
//         ctx.moveTo(violin.min, y);
//         coords.forEach(function (_ref3) {
//           var v = _ref3.v,
//               estimate = _ref3.estimate;
//           ctx.lineTo(v, y - estimate * _factor);
//         });
//         ctx.lineTo(violin.max, y);
//         ctx.moveTo(violin.min, y);
//         coords.forEach(function (_ref4) {
//           var v = _ref4.v,
//               estimate = _ref4.estimate;
//           ctx.lineTo(v, y + estimate * _factor);
//         });
//         ctx.lineTo(violin.max, y);
//       }
//
//       ctx.stroke();
//       ctx.fill();
//       ctx.closePath();
//
//       this._drawOutliers(vm, violin, ctx, vert);
//
//       ctx.restore();
//     },
//     _getBounds: function _getBounds() {
//       var vm = this._view;
//       var vert = this.isVertical();
//       var violin = vm.violin;
//
//       if (vert) {
//         var x = vm.x,
//             width = vm.width;
//         var x0 = x - width / 2;
//         return {
//           left: x0,
//           top: violin.max,
//           right: x0 + width,
//           bottom: violin.min
//         };
//       }
//
//       var y = vm.y,
//           height = vm.height;
//       var y0 = y - height / 2;
//       return {
//         left: violin.min,
//         top: y0,
//         right: violin.max,
//         bottom: y0 + height
//       };
//     },
//     height: function height() {
//       var vm = this._view;
//       return vm.base - Math.min(vm.violin.min, vm.violin.max);
//     },
//     getArea: function getArea() {
//       var vm = this._view;
//       var iqr = Math.abs(vm.violin.max - vm.violin.min);
//
//       if (this.isVertical()) {
//         return iqr * vm.width;
//       }
//
//       return iqr * vm.height;
//     }
//   });
//
//   var verticalDefaults = {
//     scales: {
//       yAxes: [{
//         type: 'arrayLinear'
//       }]
//     }
//   };
//   var horizontalDefaults = {
//     scales: {
//       xAxes: [{
//         type: 'arrayLinear'
//       }]
//     }
//   };
//   var array = {
//     _elementOptions: function _elementOptions() {
//       return {};
//     },
//     updateElement: function updateElement(elem, index, reset) {
//       var dataset = this.getDataset();
//       var custom = elem.custom || {};
//
//       var options = this._elementOptions();
//
//       Chart.controllers.bar.prototype.updateElement.call(this, elem, index, reset);
//       var resolve = Chart.helpers.options.resolve;
//       var keys = ['outlierRadius', 'itemRadius', 'itemStyle', 'itemBackgroundColor', 'itemBorderColor', 'outlierColor', 'medianColor', 'hitPadding']; // Scriptable options
//
//       var context = {
//         chart: this.chart,
//         dataIndex: index,
//         dataset: dataset,
//         datasetIndex: this.index
//       };
//       keys.forEach(function (item) {
//         elem._model[item] = resolve([custom[item], dataset[item], options[item]], context, index);
//       });
//     },
//     _calculateCommonModel: function _calculateCommonModel(r, data, container, scale) {
//       if (container.outliers) {
//         r.outliers = container.outliers.map(function (d) {
//           return scale.getPixelForValue(Number(d));
//         });
//       }
//
//       if (Array.isArray(data)) {
//         r.items = data.map(function (d) {
//           return scale.getPixelForValue(Number(d));
//         });
//       }
//     }
//   };
//
//   var defaults$1 = {
//     tooltips: {
//       callbacks: {
//         label: function label(item, data) {
//           var datasetLabel = data.datasets[item.datasetIndex].label || '';
//           var value = data.datasets[item.datasetIndex].data[item.index];
//           var b = asBoxPlotStats(value);
//           var label = "".concat(datasetLabel, " ").concat(typeof item.xLabel === 'string' ? item.xLabel : item.yLabel);
//
//           if (!b) {
//             return label + 'NaN';
//           }
//
//           return "".concat(label, " (min: ").concat(b.min, ", q1: ").concat(b.q1, ", median: ").concat(b.median, ", q3: ").concat(b.q3, ", max: ").concat(b.max, ")");
//         }
//       }
//     }
//   };
//   Chart.defaults.boxplot = Chart.helpers.merge({}, [Chart.defaults.bar, verticalDefaults, defaults$1]);
//   Chart.defaults.horizontalBoxplot = Chart.helpers.merge({}, [Chart.defaults.horizontalBar, horizontalDefaults, defaults$1]);
//
//   var boxplot = _objectSpread({}, array, {
//     dataElementType: Chart.elements.BoxAndWhiskers,
//     _elementOptions: function _elementOptions() {
//       return this.chart.options.elements.boxandwhiskers;
//     },
//
//     /**
//      * @private
//      */
//     _updateElementGeometry: function _updateElementGeometry(elem, index, reset) {
//       Chart.controllers.bar.prototype._updateElementGeometry.call(this, elem, index, reset);
//
//       elem._model.boxplot = this._calculateBoxPlotValuesPixels(this.index, index);
//     },
//
//     /**
//      * @private
//      */
//     _calculateBoxPlotValuesPixels: function _calculateBoxPlotValuesPixels(datasetIndex, index) {
//       var scale = this._getValueScale();
//
//       var data = this.chart.data.datasets[datasetIndex].data[index];
//
//       if (!data) {
//         return null;
//       }
//
//       var v = asBoxPlotStats(data);
//       var r = {};
//       Object.keys(v).forEach(function (key) {
//         if (key !== 'outliers') {
//           r[key] = scale.getPixelForValue(Number(v[key]));
//         }
//       });
//
//       this._calculateCommonModel(r, data, v, scale);
//
//       return r;
//     }
//   });
//   /**
//    * This class is based off controller.bar.js from the upstream Chart.js library
//    */
//
//
//   var BoxPlot = Chart.controllers.boxplot = Chart.controllers.bar.extend(boxplot);
//   var HorizontalBoxPlot = Chart.controllers.horizontalBoxplot = Chart.controllers.horizontalBar.extend(boxplot);
//
//   var defaults$2 = {};
//   Chart.defaults.violin = Chart.helpers.merge({}, [Chart.defaults.bar, verticalDefaults, defaults$2]);
//   Chart.defaults.horizontalViolin = Chart.helpers.merge({}, [Chart.defaults.horizontalBar, horizontalDefaults, defaults$2]);
//
//   var controller = _objectSpread({}, array, {
//     dataElementType: Chart.elements.Violin,
//     _elementOptions: function _elementOptions() {
//       return this.chart.options.elements.violin;
//     },
//
//     /**
//      * @private
//      */
//     _updateElementGeometry: function _updateElementGeometry(elem, index, reset) {
//       Chart.controllers.bar.prototype._updateElementGeometry.call(this, elem, index, reset);
//
//       var custom = elem.custom || {};
//
//       var options = this._elementOptions();
//
//       elem._model.violin = this._calculateViolinValuesPixels(this.index, index, custom.points !== undefined ? custom.points : options.points);
//     },
//
//     /**
//      * @private
//      */
//     _calculateViolinValuesPixels: function _calculateViolinValuesPixels(datasetIndex, index, points) {
//       var scale = this._getValueScale();
//
//       var data = this.chart.data.datasets[datasetIndex].data[index];
//       var violin = asViolinStats(data);
//       var range = violin.max - violin.min;
//       var samples = [];
//       var inc = range / points;
//
//       for (var v = violin.min; v <= violin.max; v += inc) {
//         samples.push(v);
//       }
//
//       if (samples[samples.length - 1] !== violin.max) {
//         samples.push(violin.max);
//       }
//
//       var coords = violin.coords || violin.kde(samples).map(function (v) {
//         return {
//           v: v[0],
//           estimate: v[1]
//         };
//       });
//       var r = {
//         min: scale.getPixelForValue(violin.min),
//         max: scale.getPixelForValue(violin.max),
//         median: scale.getPixelForValue(violin.median),
//         coords: coords.map(function (_ref) {
//           var v = _ref.v,
//               estimate = _ref.estimate;
//           return {
//             v: scale.getPixelForValue(v),
//             estimate: estimate
//           };
//         }),
//         maxEstimate: coords.reduce(function (a, d) {
//           return Math.max(a, d.estimate);
//         }, Number.NEGATIVE_INFINITY)
//       };
//
//       this._calculateCommonModel(r, data, violin, scale);
//
//       return r;
//     }
//   });
//   /**
//    * This class is based off controller.bar.js from the upstream Chart.js library
//    */
//
//
//   var Violin$1 = Chart.controllers.violin = Chart.controllers.bar.extend(controller);
//   var HorizontalViolin = Chart.controllers.horizontalViolin = Chart.controllers.horizontalBar.extend(controller);
//
//   var helpers = Chart.helpers;
//   var ArrayLinearScaleOptions = helpers.merge({}, [commonScaleOptions, Chart.scaleService.getScaleDefaults('linear')]);
//   var ArrayLinearScale = Chart.scaleService.getScaleConstructor('linear').extend({
//     getRightValue: function getRightValue$$1(rawValue) {
//       return Chart.LinearScaleBase.prototype.getRightValue.call(this, getRightValue(rawValue));
//     },
//     determineDataLimits: function determineDataLimits() {
//       commonDataLimits.call(this); // Common base implementation to handle ticks.min, ticks.max, ticks.beginAtZero
//
//       this.handleTickRangeOptions();
//     }
//   });
//   Chart.scaleService.registerScaleType('arrayLinear', ArrayLinearScale, ArrayLinearScaleOptions);
//
//   var helpers$1 = Chart.helpers;
//   var ArrayLogarithmicScaleOptions = helpers$1.merge({}, [commonScaleOptions, Chart.scaleService.getScaleDefaults('logarithmic')]);
//   var ArrayLogarithmicScale = Chart.scaleService.getScaleConstructor('logarithmic').extend({
//     getRightValue: function getRightValue$$1(rawValue) {
//       return Chart.LinearScaleBase.prototype.getRightValue.call(this, getRightValue(rawValue));
//     },
//     determineDataLimits: function determineDataLimits() {
//       var _this = this;
//
//       // Add whitespace around bars. Axis shouldn't go exactly from min to max
//       var tickOpts = this.options.ticks;
//       this.minNotZero = null;
//       commonDataLimits.call(this, function (boxPlot) {
//         var value = boxPlot[tickOpts.minStats];
//
//         if (value !== 0 && (_this.minNotZero === null || value < _this.minNotZero)) {
//           _this.minNotZero = value;
//         }
//       });
//       this.min = helpers$1.valueOrDefault(tickOpts.min, this.min - this.min * 0.05);
//       this.max = helpers$1.valueOrDefault(tickOpts.max, this.max + this.max * 0.05);
//
//       if (this.min === this.max) {
//         if (this.min !== 0 && this.min !== null) {
//           this.min = Math.pow(10, Math.floor(helpers$1.log10(this.min)) - 1);
//           this.max = Math.pow(10, Math.floor(helpers$1.log10(this.max)) + 1);
//         } else {
//           this.min = 1;
//           this.max = 10;
//         }
//       }
//     }
//   });
//   Chart.scaleService.registerScaleType('arrayLogarithmic', ArrayLogarithmicScale, ArrayLogarithmicScaleOptions);
//
//   exports.BoxAndWhiskers = BoxAndWiskers;
//   exports.Violin = Violin;
//   exports.ArrayLinearScale = ArrayLinearScale;
//   exports.ArrayLogarithmicScale = ArrayLogarithmicScale;
//   exports.BoxPlot = BoxPlot;
//   exports.HorizontalBoxPlot = HorizontalBoxPlot;
//   exports.HorizontalViolin = HorizontalViolin;
//
//   Object.defineProperty(exports, '__esModule', { value: true });
//
// }));
// boxPlot e
