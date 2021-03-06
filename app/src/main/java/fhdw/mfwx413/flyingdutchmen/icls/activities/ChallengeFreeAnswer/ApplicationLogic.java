package fhdw.mfwx413.flyingdutchmen.icls.activities.ChallengeFreeAnswer;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import fhdw.mfwx413.flyingdutchmen.icls.data.Challenge;
import fhdw.mfwx413.flyingdutchmen.icls.data.UserProgressDatabase;
import fhdw.mfwx413.flyingdutchmen.icls.exceptions.InvalidCorrectAnswerTypeException;
import fhdw.mfwx413.flyingdutchmen.icls.exceptions.UserProgressNotFoundException;
import fhdw.mfwx413.flyingdutchmen.icls.utilities.Navigation;

/**
 * Responsibility: Jonas Krabs
 */
public class ApplicationLogic {

    private final Data mData;
    private final Gui mGui;
    private final Activity mActivity;

    public ApplicationLogic(Data data, Gui gui, Activity activity) {
        mData = data;
        mGui = gui;
        mActivity = activity;
        initialUpdateGui();
    }

    // initialize the Gui by setting the questiontext
    private void initialUpdateGui() {
        Challenge challenge;
        int currentChallengeId;

        currentChallengeId = mData.getmCurrentChallengeId();
        challenge = mData.getmDueChallengesOfUserInFile().getChallenge(currentChallengeId);

        mGui.setQuestionText(challenge.getmQuestiontext());
    }

    //method that is invoked if the confirm button is clicked
    public void onButtonConfirmFreeAnswerClicked()throws InvalidCorrectAnswerTypeException{
        int challengeId = mData.getmCurrentChallengeId();
        Challenge challenge;
        String givenAnswer;
        boolean isAnswerCorrect;

        //save the given answer from the input textfield
        givenAnswer = mGui.getmGivenAnswer().getText().toString();

        challenge = mData.getmDueChallengesOfUserInFile().getChallenge(challengeId);

        //analyze if the given answer was correct or not (case insensitive)
        switch (challenge.getmCorrectAnswer()){
            // if one, there is only one answer saved in the challenge and this is the right one
            case 1:
                //noinspection RedundantIfStatement
                if (givenAnswer.equalsIgnoreCase(challenge.getmAnswerOne())){
                    isAnswerCorrect = true;
                }
                else {
                    isAnswerCorrect = false;
                }
                break;
            //if three, there are two answers saved in the challenge and both are right
            case 3:
                //noinspection RedundantIfStatement
                if (givenAnswer.equalsIgnoreCase(challenge.getmAnswerOne()) || givenAnswer.equalsIgnoreCase(challenge.getmAnswerTwo())){
                    isAnswerCorrect = true;
                }
                else {
                    isAnswerCorrect = false;
                }
                break;
            //if seven, there are three answers saved in the challenge and all three are right
            case 7:
                //noinspection RedundantIfStatement
                if (givenAnswer.equalsIgnoreCase(challenge.getmAnswerOne()) || givenAnswer.equalsIgnoreCase(challenge.getmAnswerTwo()) || givenAnswer.equalsIgnoreCase(challenge.getmAnswerThree())){
                    isAnswerCorrect = true;
                }
                else {
                    isAnswerCorrect = false;
                }
                break;
            default:
                //if there is another answer than 1,3 or 7 this answertype is invalid and the method throws an InvalidCorrectAnswerTypeException
                throw new InvalidCorrectAnswerTypeException("ChallengeFreeAnswer::ApplicationLogic::onButtonConfirmFreeAnswerClicked: Ungültiger Wert für CorrectAnswer: " + challenge.getmCorrectAnswer());
        }

        try {
            //the userprogress has to be updated after answering the question
            updateUserProgress(isAnswerCorrect);

            //call the Feedback-Activity and send the required data
            Navigation.startActivityFeedbackChallengeRest(mData.getActivity(), mData.getmDueChallengesOfUserInFile(), mData.getmCurrentChallengeId(), mData.getmChosenUser(), mData.getmChosenFile(), isAnswerCorrect, mData.getmUserProgresses());

        }
        //if something went wrong by updating the userprogresses the startActivity is called
        catch (UserProgressNotFoundException e){
            Log.e("ICLS-LOG", "ChallengeFreeAnswer::ApplicationLogic::onButtonConfirmFreeAnswerClicked: ", e);
            showErrorUnexpectedError();
            Navigation.startActivityStartMenu(mActivity);
        }
    }

    //start activity ChooseFile and send required data
    public void goBackToChooseFile() {
        Navigation.startActivityChooseIndexCard(mData.getActivity(), mData.getmChosenUser());
    }

    //for the user it is not important to have detailed information about the error
    //detailed information are always given in the logs
    public void showErrorUnexpectedError(){
        Toast.makeText(mActivity, "Unerwarteter Fehler", Toast.LENGTH_SHORT).show();
    }

    //update userprogress after answering the question
    private void updateUserProgress(boolean isAnswerCorrect) throws UserProgressNotFoundException{
        boolean userProgressFound = false;
        //search for the right progress in the userProgressCollection
        for (int i = 0; i < mData.getmUserProgresses().getSize(); i++){

            if (mData.getmUserProgresses().getUserProgress(i).getmChallengeID() == mData.getmDueChallengesOfUserInFile().getChallenge(mData.getmCurrentChallengeId()).getmID()){
                int actualTimeClass = mData.getmUserProgresses().getUserProgress(i).getmPeriodClass();

                //if the progress was found everything is fine and the progress can be updated
                userProgressFound = true;
                mData.getmUserProgresses().getUserProgress(i).setCurrentTimeStamp();

                //noinspection PointlessBooleanExpression
                if (isAnswerCorrect == true) {
                    if (actualTimeClass < 5 ) {
                        mData.getmUserProgresses().getUserProgress(i).setmPeriodClass(actualTimeClass + 1);
                    }
                }
                else {
                    if (actualTimeClass > 1 ) {
                        mData.getmUserProgresses().getUserProgress(i).setmPeriodClass(actualTimeClass - 1);
                    }
                }
                //save the updated progress in the csv
                UserProgressDatabase.writeSpecificUserProgresses(mData.getmUserProgresses(), mData.getmChosenUser().getName(), mActivity);
                break;
            }
        }
        //if the userprogress was not found the method throws an UserProgressNotFoundException
        //noinspection PointlessBooleanExpression
        if (userProgressFound == false){
            throw new UserProgressNotFoundException("ChallengeFreeAnswer::ApplicationLogic::updateUserProgress:"
                    + " CurrentUserName: " + mData.getmChosenUser().getName()
                    + " ChallengeID:" + mData.getmDueChallengesOfUserInFile().getChallenge(mData.getmCurrentChallengeId()).getmID());
        }
    }

}
