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

package edu.kit.iks.cryptographics.caesar.controller;

import org.xnap.commons.i18n.I18n;

import edu.kit.iks.cryptographics.caesar.model.TrialModel;
import edu.kit.iks.cryptographics.caesar.view.trial.TrialView;
import edu.kit.iks.cryptographics.caesar.view.trial.partial.EncryptName;
import edu.kit.iks.cryptographics.caesar.view.trial.partial.EnterName;
import edu.kit.iks.cryptographicslib.framework.controller.AbstractSteppableVisualizationController;
import edu.kit.iks.cryptographicslib.framework.model.AbstractVisualizationInfo;
import edu.kit.iks.cryptographicslib.util.Configuration;
import edu.kit.iks.cryptographicslib.util.Random;

/**
 * @author Christian Dreher
 *
 */
public class TrialController extends AbstractSteppableVisualizationController {

    /**
     * Localized strings used in this view.
     * 
     * @author Christian Dreher <uaeef@student.kit.edu>
     */
    private static class Strings {
        
        /**
         * Localization instance.
         */
        private static I18n i18n = Configuration.getInstance().getI18n(TrialController.class);
        
        // Navigation labels and various
        private static String nextButtonLabel = Strings.i18n.tr("Skip experiment");
        private static String backButtonLabel = Strings.i18n.tr("Back to demonstration");
        private static String stepButtonLabelRandomName = Strings.i18n.tr("Use random name");
        private static String stepButtonLabel = Strings.i18n.tr("Proceed");
        
        private static String trialExplanation = Strings.i18n.tr("Now lets try to encrypt your name with "
                + "the key {0}. Just enter your name in the text field below or press the button "
                + "to use a random one.", TrialController.randomKey);
        private static String encryptNameExplanation = Strings.i18n.tr("Okay, lets go. If you need help, tap "
                + "the button in the upper right corner.");
    };
   
    /**
     * Keys below 5 are not very challenging, but 8 is enough
     */
    private static int randomKey = Random.integer(5, 8);
    
    private TrialModel model = new TrialModel();
    
    private EnterName enterName;
    private EncryptName encryptName;
    
    /**
     * @param visualizationInfo
     */
    public TrialController(AbstractVisualizationInfo visualizationInfo) {
        super(visualizationInfo);
    }

    /* (non-Javadoc)
     * @see edu.kit.iks.cryptographicslib.framework.controller.AbstractSteppableVisualizationController
     * #loadView(edu.kit.iks.cryptographicslib.framework.controller
     * .AbstractSteppableVisualizationController.RunningOrderHelper)
     */
    @Override
    public final void loadView(final RunningOrderHelper roh) {
        VariableHelper vh = new VariableHelper();
        
        vh.add("nextButtonLabel", TrialController.Strings.nextButtonLabel);
        vh.add("backButtonLabel", TrialController.Strings.backButtonLabel);
        vh.add("stepButtonLabelRandomName", TrialController.Strings.stepButtonLabelRandomName);
        vh.add("stepButtonLabel", TrialController.Strings.stepButtonLabel);
        
        this.view = new TrialView(this, vh.toList());
        
        this.defineRunningOrder(roh);
    }
    
    @Override
    public void viewLoaded() {
        this.enterName.focusInput();
    }

    /* (non-Javadoc)
     * @see edu.kit.iks.cryptographicslib.framework.controller.AbstractController#unloadView()
     */
    @Override
    public final void unloadView() {
        this.view = null;
    }

    /* (non-Javadoc)
     * @see edu.kit.iks.cryptographicslib.framework.controller.AbstractVisualizationController
     * #routeAction(java.lang.String)
     */
    @Override
    public final boolean routeAction(final String callerId) {
        switch (callerId) {
            case "randomName":
                this.useRandomName();
                break;
            case "customName":
                this.useCustomName();
                break;
            case "keyPressed":
                String input = this.view().getUserInput();
                this.keyPressed(input);
                break;
            default:
                return false;
        }
    
        return true;
    }
    
    /* (non-Javadoc)
     * @see edu.kit.iks.cryptographicslib.framework.controller.AbstractVisualizationController#helpAction()
     */
    @Override
    public final String helpAction() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Default Action.
     * 
     * Overridden, because the partial view will be reused for the
     * next step, therefore the default behavior is undesired
     * 
     * @see edu.kit.iks.cryptographicslib.framework.controller.AbstractSteppableVisualizationController#indexAction()
     */
    @Override
    protected final void indexAction() {
        this.view().useStringKeyboard();
        this.view().showKeyboard();
        this.view().setStepButtonAction("randomName");
        this.view().setKeyboardAction("keyPressed");
        super.indexAction();
    }
    
    protected final void keyPressed(String input) {
        if (input.equals("")) {
            this.view().stepButtonLabelRandomName();
            this.view().setStepButtonAction("randomName");
        } else {
            this.view().stepButtonLabelProceed();
            this.view().setStepButtonAction("customName");
        }
        
        this.model.setName(input);
        this.enterName.setInputValue(this.model);
    }
    
    protected final void useRandomName() {
        this.model.useRandomName();
        this.goToEncrypt();
    }
    
    protected final void useCustomName() {
        this.goToEncrypt();
    }
    
    protected final void goToEncrypt() {
        this.encryptName.setName(this.model.getName());
        this.encryptName.setKey(TrialController.randomKey);
        this.view().hideStepButton();
        this.view().hideKeyboard();
        this.defaultStepAction();
        this.view().useCharKeyboard();
        this.view().showKeyboard();
    }
    
    /**
     * Helper to define the running order.
     * 
     * @param roh Running order helper
     */
    private void defineRunningOrder(final RunningOrderHelper roh) {
        roh.enqueue(this.prepareEnterName());
        roh.enqueue(this.prepareEncryptName());
    }
    
    private EnterName prepareEnterName() {
        VariableHelper vh = new VariableHelper();
        
        vh.add("explanation", TrialController.Strings.trialExplanation);
        
        this.enterName = new EnterName(vh.toList());
        return this.enterName;
    }
    
    private EncryptName prepareEncryptName() {
        VariableHelper vh = new VariableHelper();
        
        vh.add("explanation", TrialController.Strings.encryptNameExplanation);
        
        this.encryptName = new EncryptName(vh.toList());
        return this.encryptName;
    }
    
    private TrialView view() {
        return (TrialView) this.view;
    }
}
