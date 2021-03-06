/**
 * Copyright (c) 2014 Matthias Jaenicke <matthias.jaenicke@student.kit.edu>,
 * Matthias Plappert <undkc@student.kit.edu>,
 * Julien Duman <uncyc@student.kit.edu>, 
 * Christian Dreher <uaeef@student.kit.edu>,
 * Wasilij Beskorovajnov <uajkm@student.kit.edu> and 
 * Aydin Tekin <aydin.tekin@student.kit.edu>
 * 
 * Released under the MIT license (refer to LICENSE.md)
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package edu.kit.iks.cryptographicslib.util;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

/**
 * @author Christian Dreher
 *
 */
public abstract class Highlighter {
    
    public static class COLORS_NORMAL {
        public final static Color FONT = Color.BLACK;
        public final static Color BORDER = Color.LIGHT_GRAY;
        public final static Color BACKGROUND = Color.WHITE;
    }
    
    public static class COLORS_SUCCESS {
        public final static Color FONT = new Color(0x3c763d);
        public final static Color BORDER = new Color(0xd6e9c6);
        public final static Color BACKGROUND = new Color(0xdff0d8);
    }
    
    public static class COLORS_INFO {
        public final static Color FONT = new Color(0x31708f);
        public final static Color BORDER = new Color(0xbce8f1);
        public final static Color BACKGROUND = new Color(0xd9edf7);
    }
    
    public static class COLORS_WARNING {
        public final static Color FONT = new Color(0x8a6d3b);
        public final static Color BORDER = new Color(0xfaebcc);
        public final static Color BACKGROUND = new Color(0xfcf8e3);
    }
    
    public static class COLORS_ERROR {
        public final static Color FONT = new Color(0xa94442);
        public final static Color BORDER = new Color(0xebccd1);
        public final static Color BACKGROUND = new Color(0xf2dede);
    }
   
    public static void normal(JComponent jc) {
        jc.setForeground(COLORS_NORMAL.FONT);
        jc.setBackground(COLORS_NORMAL.BACKGROUND);
        jc.setBorder(BorderFactory.createLineBorder(COLORS_NORMAL.BORDER));
        jc.setOpaque(true);
    }
    
    public static void normalNoBorder(JComponent jc) {
        jc.setForeground(COLORS_NORMAL.FONT);
        jc.setBackground(COLORS_NORMAL.BACKGROUND);
        jc.setBorder(null);
        jc.setOpaque(true);
    }
    
    public static void success(JComponent jc) {
        jc.setForeground(COLORS_SUCCESS.FONT);
        jc.setBackground(COLORS_SUCCESS.BACKGROUND);
        jc.setBorder(BorderFactory.createLineBorder(COLORS_SUCCESS.BORDER));
        jc.setOpaque(true);
    }
    
    public static void info(JComponent jc) {
        jc.setForeground(COLORS_INFO.FONT);
        jc.setBackground(COLORS_INFO.BACKGROUND);
        jc.setBorder(BorderFactory.createLineBorder(COLORS_INFO.BORDER));
        jc.setOpaque(true);
    }
    
    public static void warning(JComponent jc) {
        jc.setForeground(COLORS_WARNING.FONT);
        jc.setBackground(COLORS_WARNING.BACKGROUND);
        jc.setBorder(BorderFactory.createLineBorder(COLORS_WARNING.BORDER));
        jc.setOpaque(true);
    }
    
    public static void error(JComponent jc) {
        jc.setForeground(COLORS_ERROR.FONT);
        jc.setBackground(COLORS_ERROR.BACKGROUND);
        jc.setBorder(BorderFactory.createLineBorder(COLORS_ERROR.BORDER));
        jc.setOpaque(true);
    }
}
