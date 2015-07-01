/*
 * Style File - jQuery plugin for styling file input elements
 *  
 * Copyright (c) 2007-2008 Mika Tuupola
 *
 * Licensed under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 *
 * Based on work by Shaun Inman
 *   http://www.shauninman.com/archive/2007/09/10/styling_file_inputs_with_css_and_the_dom
 *
 * Revision: $Id: jquery.filestyle.js 303 2008-01-30 13:53:24Z tuupola $
 *
 */

(function($) {

	$.browser = {};
	$.browser.mozilla = /mozilla/.test(navigator.userAgent.toLowerCase()) && !/webkit/.test(navigator.userAgent.toLowerCase());
	
    $.fn.filestyle = function(options) {
                
        /* TODO: This should not override CSS. */
        var settings = {
            width : 250
        };
                
        if(options) {
            $.extend(settings, options);
        };
                        
        return this.each(function() {
            
            var self = this;
            var wrapper = $("<div>")
                            .css({
                                "width": settings.imagewidth + "px",
                                "height": settings.imageheight + "px",
                                "background": "url(" + settings.image + ") 0 0 no-repeat",
                                "background-position": "right",
                                "display": "inline",
                                "position": "absolute",
                                "overflow": "hidden",
                                "background-size" : "100%"
                            });
                            
            var filename = $('<input class="file">')
            				.addClass($(self).attr("class"))
                             .addClass(settings.validationclass?settings.validationclass:"")
                             .css({
                                 "display": "inline",
                                 "width": settings.width + "px"
                             });
            filename.attr('type' , 'text');
            filename.attr('id', $(self).attr('id')+'_fake');
            filename.attr('data-prompt-position', $(self).attr('data-prompt-position'));
            filename.attr('placeholder',$(self).attr('placeholder'));
            if(settings.isdisabled)
            	filename.attr('disabled','disabled');
            $(self).before(filename);
            $(self).wrap(wrapper);
            $(self).css({
                        "position": "relative",
                        "height": settings.imageheight + "px",
                        "width": settings.width + "px",
                        "display": "inline",
                        "cursor": "pointer",
                        "opacity": "0.0"
                    });
            
            $(self).attr("name", settings.name);
            
            if(settings.isdisabled)
            	$(self).attr('disabled','disabled');
            
            if ($.browser.mozilla) {
                if (/Win/.test(navigator.platform)) {
                    $(self).css("margin-left", "-142px");                    
                } else {
                    $(self).css("margin-left", "-168px");                    
                };
            } else {
                $(self).css("margin-left", settings.imagewidth - settings.width + "px");                
            };

            $(self).bind("change", function() {
                filename.val($(self).val());
            });
      
        });
        

    };
    
})(jQuery);
