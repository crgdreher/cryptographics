/**
 * Copyright (c) 2014 Christian Dreher <uaeef@student.kit.edu>
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

package edu.kit.iks.cryptographics.caesar.view.trial.partial;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

import edu.kit.iks.cryptographicslib.common.view.partial.EncryptDecryptView;
import edu.kit.iks.cryptographicslib.framework.view.partial.AbstractPartialView;

/**
 * @author Christian Dreher
 *
 */
public class EncryptName extends AbstractPartialView {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -5538835293926835178L;
    
    private EncryptDecryptView encryptNameView; 
    private String name;
    private String key;
    
    /**
     * @param variables
     */
    public EncryptName(List<SimpleEntry<String, String>> variables) {
        super(variables);
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setKey(int key) {
        this.key = Integer.toString(key);
    }

    /* (non-Javadoc)
     * @see edu.kit.iks.cryptographicslib.framework.view.partial.AbstractPartialView#preparePartialView()
     */
    @Override
    public void preparePartialView() {
        this.addText(this.getVariableValue("explanation"));
        
        this.encryptNameView = new EncryptDecryptView(this.name, this.key);
        this.encryptNameView.enable(0);
        this.addElement(this.encryptNameView);
    }

}
