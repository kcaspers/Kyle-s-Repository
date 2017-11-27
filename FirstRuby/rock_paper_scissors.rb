class RockPaperScissors
  #take choice from user and randomly generate com choice
  continue = true

  while continue == true do


    comChoice = ''
    comChoiceInt = rand(3)
    if comChoiceInt.equal?(0)
      comChoice = 'rock'
    elsif comChoiceInt.equal?(1)
      comChoice = 'paper'
    else #i.e. equal?(2)
      comChoice = 'scissors'
    end

    puts('Choose rock, paper or scissors')
    userChoice = (gets.chomp).downcase

    #make sure they picked one of the three options
    if userChoice == 'rock' || userChoice == 'paper' || userChoice == 'scissors'
      #this is good, do nothing
    else
      puts("That was not valid input.")
    end

    #now process their choice against the computers
    if userChoice == comChoice
      #they are equal, declare a tie
      puts("you chose #{userChoice}. The computer also chose #{comChoice}. It is a tie.")

    elsif userChoice == 'rock' && comChoice == 'paper' ||
        userChoice == 'scissors' && comChoice == 'rock' ||
        userChoice == 'paper' && comChoice == 'scissors'
      #they lose
      puts("You chose #{userChoice}. The computer chose #{comChoice}. You lose.")

    elsif userChoice == 'rock' && comChoice == 'scissors' ||
        userChoice == 'scissors' && comChoice == 'paper' ||
        userChoice == 'paper' && comChoice == 'rock'
      #they win
      puts("You chose #{userChoice}. The computer chose #{comChoice}. You win.")
    end

    puts("Would you like to play again? y/n")
    userContinue = (gets.chomp).downcase
    if userContinue == 'y'
      continue = true
    elsif userContinue == 'n'
      puts('Goodbye')
      continue = false
    else
      puts('Unrecognized input')
      continue = false
    end
  end
end
