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
package edu.kit.iks.Cryptographics.Caesar2.Demo;

import edu.kit.iks.CryptographicsLib.views.VisualizationView;

/**
 * @author Christian Dreher <uaeef@student.kit.edu>
 *
 */
public class DemoView extends VisualizationView {
	
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 2155655628333021301L;

	public DemoView() {
		this.setNextButton("Additional info");
		this.setBackButton("Back to Introduction");
	}
}
