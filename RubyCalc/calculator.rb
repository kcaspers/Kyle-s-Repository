class Calculator

  puts('Give me a number.')
  firstNum = gets.chomp
  puts('Give me a second number')
  secondNum = gets.chomp
  puts("You gave me #{firstNum} and #{secondNum}")

  puts('Should I do function (1), (2), (3) or (4)?')
  #each function should do something strange and the user should guess what is happening.
  userChoice = gets.chomp

  if(userChoice.equal?(1))

  elsif (userChoice.equal?(2))

  elsif (userChoice.equal?(3))

  elsif (userChoice.equal?(4))
    #divide
  end

  #helper methods
  def functionOne(first, second)
    result = 0

    return result
  end

  def functionTwo(first, second)
    result = 0

    return result
  end

  def functionThree(first, second)
    result = 0

    return result
  end

  def functionFour(first, second)
    result = 0

    return result
  end
end

