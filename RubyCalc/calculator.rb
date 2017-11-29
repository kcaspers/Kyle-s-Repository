#note the use of 'require_relative'. I use this because they are in the same directory
require_relative 'functions'

class Calculator

  functions = Functions.new

  puts('Give me a number.')
  firstNum = gets.chomp.to_i
  puts('Give me a second number')
  secondNum = gets.chomp.to_i
  puts("You gave me #{firstNum} and #{secondNum}")

  puts('Should I do function (1), (2), (3) or (4)?')
  #each function should do something strange and the user should guess what is happening
  #the functions only use the values given, and they will use each value a max of 2 times
  #the functions will only employ addition, subtraction, multiplication and division
  invalidInput = true

  while invalidInput
    userChoice = gets.chomp
    if userChoice != 

    end
  end


  firstNum = firstNum.to_f
  secondNum = secondNum.to_f

  if userChoice == '1'
    result = functions.functionOne(firstNum, secondNum)
  elsif userChoice == '2'
    result = functions.functionTwo(firstNum, secondNum)
  elsif userChoice == '3'
    result = functions.functionThree(firstNum, secondNum)
  elsif userChoice == '4'
    result = functions.functionFour(firstNum, secondNum)
  end

#output the result, then prompt them to figure out what is happening
#multi-choice might be too easy, some kind of sandbox for them to re-create it
  puts("The result is #{result.round(2)}")

end



