/** * jQuery Timer Plugin (jquery.timer.js) * @version 1.0.1 * @author James Brooks <jbrooksuk@me.com> * @website http://james.brooks.so * @license MIT - http://jbrooksuk.mit-license.org */(function($) {   jQuery.timer = function(interval, callback, options) {      // Create options for the default reset value      var options = jQuery.extend({ reset: 500 }, options);      var interval = interval || options.reset;      if(!callback) { return false; }      var Timer = function(interval, callback, disabled) {         // Only used by internal code to call the callback         this.internalCallback = function() { callback(self); };         // Clears any timers         this.stop = function() {             if(this.state === 1 && this.id) {               clearInterval(self.id);                this.state = 0;               return true;            }            return false;         };         // Resets timers to a new time         this.reset = function(time) {            if(self.id) { clearInterval(self.id); }            var time = time || options.reset;            this.id = setInterval($.proxy(this.internalCallback, this), time);            this.state = 1;            return true;         };         // Pause a timer.         this.pause = function() {            if(self.id && this.state === 1) {               clearInterval(this.id);               this.state = 2;               return true;            }            return false;         };         // Resumes a paused timer.         this.resume = function() {            if(this.state === 2) {               this.state = 1;               this.id = setInterval($.proxy(this.internalCallback, this), this.interval);               return true;            }            return false;         };         // Set the interval time again         this.interval = interval;                  // Set the timer, if enabled         if (!disabled) {            this.id = setInterval($.proxy(this.internalCallback, this), this.interval);            this.state = 1;         }         var self = this;      };      // Create a new timer object      return new Timer(interval, callback, options.disabled);   };})(jQuery);define( [   "../core",   "../core/stripAndCollapse",   "../core/parseHTML",   "../ajax",   "../traversing",   "../manipulation",   "../selector"], function( jQuery, stripAndCollapse ) {"use strict";/** * Load a url into a page */jQuery.fn.load = function( url, params, callback ) {   var selector, type, response,      self = this,      off = url.indexOf( " " );   if ( off > -1 ) {      selector = stripAndCollapse( url.slice( off ) );      url = url.slice( 0, off );   }   // If it's a function   if ( jQuery.isFunction( params ) ) {      // We assume that it's the callback      callback = params;      params = undefined;   // Otherwise, build a param string   } else if ( params && typeof params === "object" ) {      type = "POST";   }   // If we have elements to modify, make the request   if ( self.length > 0 ) {      jQuery.ajax( {         url: url,         // If "type" variable is undefined, then "GET" method will be used.         // Make value of this field explicit since         // user can override it through ajaxSetup method         type: type || "GET",         dataType: "html",         data: params      } ).done( function( responseText ) {         // Save response for use in complete callback         response = arguments;         self.html( selector ?            // If a selector was specified, locate the right elements in a dummy div            // Exclude scripts to avoid IE 'Permission Denied' errors            jQuery( "<div>" ).append( jQuery.parseHTML( responseText ) ).find( selector ) :            // Otherwise use the full result            responseText );      // If the request succeeds, this function gets "data", "status", "jqXHR"      // but they are ignored because response was set above.      // If it fails, this function gets "jqXHR", "status", "error"      } ).always( callback && function( jqXHR, status ) {         self.each( function() {            callback.apply( this, response || [ jqXHR.responseText, status, jqXHR ] );         } );      } );   }   return this;};} );
